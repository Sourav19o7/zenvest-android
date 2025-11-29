package com.zenvest.feature_auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenvest.core.common.extensions.isValidEmail
import com.zenvest.core.common.extensions.isValidOtp
import com.zenvest.core.common.session.SessionManager
import com.zenvest.core.network.response.Resource
import com.zenvest.feature_auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val email: String = "",
    val otp: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val otpSent: Boolean = false,
    val isAuthenticated: Boolean = false,
    val isNewUser: Boolean = false,
    val hasCompletedConsent: Boolean = false
)

sealed interface AuthEvent {
    data class EmailChanged(val email: String) : AuthEvent
    data class OtpChanged(val otp: String) : AuthEvent
    data object SendOtp : AuthEvent
    data object VerifyOtp : AuthEvent
    data class GoogleSignIn(val idToken: String) : AuthEvent
    data object ClearError : AuthEvent
    data object ResendOtp : AuthEvent
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email, error = null) }
            }
            is AuthEvent.OtpChanged -> {
                _uiState.update { it.copy(otp = event.otp, error = null) }
                if (event.otp.length == 6) {
                    onEvent(AuthEvent.VerifyOtp)
                }
            }
            is AuthEvent.SendOtp -> sendOtp()
            is AuthEvent.VerifyOtp -> verifyOtp()
            is AuthEvent.GoogleSignIn -> googleSignIn(event.idToken)
            is AuthEvent.ClearError -> _uiState.update { it.copy(error = null) }
            is AuthEvent.ResendOtp -> sendOtp()
        }
    }

    private fun sendOtp() {
        val email = _uiState.value.email

        if (!email.isValidEmail()) {
            _uiState.update { it.copy(error = "Please enter a valid email address") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = authRepository.sendOtp(email)) {
                is Resource.Success -> {
                    sessionManager.saveEmail(email)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            otpSent = true
                        )
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

    private fun verifyOtp() {
        val email = _uiState.value.email
        val otp = _uiState.value.otp

        if (!otp.isValidOtp()) {
            _uiState.update { it.copy(error = "Please enter a valid 6-digit OTP") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = authRepository.verifyOtp(email, otp)) {
                is Resource.Success -> {
                    result.data?.let { authResponse ->
                        sessionManager.saveAuthToken(authResponse.token)
                        sessionManager.saveIsNewUser(authResponse.isNewUser)
                        sessionManager.saveHasCompletedConsent(authResponse.hasCompletedConsent)

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isAuthenticated = true,
                                isNewUser = authResponse.isNewUser,
                                hasCompletedConsent = authResponse.hasCompletedConsent
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message,
                            otp = ""
                        )
                    }
                }
                is Resource.Loading -> { /* Already set loading */ }
            }
        }
    }

    private fun googleSignIn(idToken: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = authRepository.googleAuth(idToken)) {
                is Resource.Success -> {
                    result.data?.let { authResponse ->
                        sessionManager.saveAuthToken(authResponse.token)
                        sessionManager.saveIsNewUser(authResponse.isNewUser)
                        sessionManager.saveHasCompletedConsent(authResponse.hasCompletedConsent)

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isAuthenticated = true,
                                isNewUser = authResponse.isNewUser,
                                hasCompletedConsent = authResponse.hasCompletedConsent
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
}
