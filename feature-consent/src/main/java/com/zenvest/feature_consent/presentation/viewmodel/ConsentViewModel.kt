package com.zenvest.feature_consent.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenvest.core.common.session.SessionManager
import com.zenvest.core.network.response.Resource
import com.zenvest.feature_consent.data.models.ConsentDuration
import com.zenvest.feature_consent.data.models.ConsentRequest
import com.zenvest.feature_consent.data.models.DataRange
import com.zenvest.feature_consent.domain.ConsentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

data class ConsentUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val consentUrl: String? = null,
    val isCompleted: Boolean = false,
    val isSkipped: Boolean = false
)

sealed interface ConsentEvent {
    data object CreateConsent : ConsentEvent
    data object SkipConsent : ConsentEvent
    data object ClearError : ConsentEvent
    data object ConsentCompleted : ConsentEvent
    data object CheckConsentStatus : ConsentEvent
}

@HiltViewModel
class ConsentViewModel @Inject constructor(
    private val consentRepository: ConsentRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConsentUiState())
    val uiState: StateFlow<ConsentUiState> = _uiState.asStateFlow()

    fun onEvent(event: ConsentEvent) {
        when (event) {
            is ConsentEvent.CreateConsent -> createConsent()
            is ConsentEvent.SkipConsent -> skipConsent()
            is ConsentEvent.ClearError -> _uiState.update { it.copy(error = null) }
            is ConsentEvent.ConsentCompleted -> {
                checkConsentStatusAndComplete()
            }
            is ConsentEvent.CheckConsentStatus -> checkConsentStatus()
        }
    }

    private fun createConsent() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val calendar = Calendar.getInstance()

            val toDate = dateFormat.format(calendar.time)
            calendar.add(Calendar.YEAR, -1)
            val fromDate = dateFormat.format(calendar.time)

            val phone = sessionManager.getPhone()
            val vua = "$phone@onemoney"

            val request = ConsentRequest(
                consentDuration = ConsentDuration(unit = "MONTH", value = "12"),
                vua = vua,
                dataRange = DataRange(from = fromDate, to = toDate)
            )

            when (val result = consentRepository.createConsent(request)) {
                is Resource.Success -> {
                    result.data?.let { response ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                consentUrl = response.url
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> { /* Already set loading */ }
            }
        }
    }

    private fun skipConsent() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = consentRepository.skipConsent()) {
                is Resource.Success -> {
                    sessionManager.saveHasCompletedConsent(true)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSkipped = true,
                            isCompleted = true
                        )
                    }
                }
                is Resource.Error -> {
                    // Even if API fails, allow user to continue
                    sessionManager.saveHasCompletedConsent(true)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSkipped = true,
                            isCompleted = true
                        )
                    }
                }
                is Resource.Loading -> { /* Already set loading */ }
            }
        }
    }

    private fun checkConsentStatusAndComplete() {
        viewModelScope.launch {
            // Check consent status from backend to confirm it was successful
            when (val result = consentRepository.getConsentStatus()) {
                is Resource.Success -> {
                    result.data?.let { response ->
                        if (response.status == "ACTIVE" || response.status == "APPROVED") {
                            sessionManager.saveHasCompletedConsent(true)
                            _uiState.update { it.copy(isCompleted = true) }
                        } else {
                            // Consent was not successful, but still mark as completed to proceed
                            sessionManager.saveHasCompletedConsent(true)
                            _uiState.update { it.copy(isCompleted = true) }
                        }
                    }
                }
                is Resource.Error -> {
                    // Even if status check fails, proceed to dashboard
                    sessionManager.saveHasCompletedConsent(true)
                    _uiState.update { it.copy(isCompleted = true) }
                }
                is Resource.Loading -> { /* Ignore */ }
            }
        }
    }

    private fun checkConsentStatus() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = consentRepository.getConsentStatus()) {
                is Resource.Success -> {
                    result.data?.let { response ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isCompleted = response.status == "ACTIVE" || response.status == "APPROVED"
                            )
                        }
                        if (response.status == "ACTIVE" || response.status == "APPROVED") {
                            sessionManager.saveHasCompletedConsent(true)
                        }
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> { /* Already set loading */ }
            }
        }
    }
}
