package com.zenvest.feature_onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenvest.core.common.extensions.isValidPhone
import com.zenvest.core.common.session.SessionManager
import com.zenvest.core.network.response.Resource
import com.zenvest.feature_onboarding.data.models.OnboardingOption
import com.zenvest.feature_onboarding.data.models.OnboardingOptions
import com.zenvest.feature_onboarding.data.models.OnboardingRequest
import com.zenvest.feature_onboarding.data.models.OnboardingStep
import com.zenvest.feature_onboarding.domain.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OnboardingUiState(
    val currentStep: OnboardingStep = OnboardingStep.FINANCIAL_SITUATION,
    val financialSituation: OnboardingOption? = null,
    val primaryGoal: OnboardingOption? = null,
    val moneyPersonality: OnboardingOption? = null,
    val riskComfort: OnboardingOption? = null,
    val savingCapacity: OnboardingOption? = null,
    val phoneNumber: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isCompleted: Boolean = false
) {
    val stepIndex: Int
        get() = OnboardingStep.entries.indexOf(currentStep)

    val totalSteps: Int
        get() = OnboardingStep.entries.size

    val canProceed: Boolean
        get() = when (currentStep) {
            OnboardingStep.FINANCIAL_SITUATION -> financialSituation != null
            OnboardingStep.PRIMARY_GOAL -> primaryGoal != null
            OnboardingStep.MONEY_PERSONALITY -> moneyPersonality != null
            OnboardingStep.RISK_COMFORT -> riskComfort != null
            OnboardingStep.SAVING_CAPACITY -> savingCapacity != null
            OnboardingStep.PHONE_NUMBER -> phoneNumber.isValidPhone()
        }
}

sealed interface OnboardingEvent {
    data class SelectFinancialSituation(val option: OnboardingOption) : OnboardingEvent
    data class SelectPrimaryGoal(val option: OnboardingOption) : OnboardingEvent
    data class SelectMoneyPersonality(val option: OnboardingOption) : OnboardingEvent
    data class SelectRiskComfort(val option: OnboardingOption) : OnboardingEvent
    data class SelectSavingCapacity(val option: OnboardingOption) : OnboardingEvent
    data class PhoneNumberChanged(val phone: String) : OnboardingEvent
    data object NextStep : OnboardingEvent
    data object PreviousStep : OnboardingEvent
    data object Submit : OnboardingEvent
    data object ClearError : OnboardingEvent
}

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    val financialSituationOptions = OnboardingOptions.financialSituations
    val primaryGoalOptions = OnboardingOptions.primaryGoals
    val moneyPersonalityOptions = OnboardingOptions.moneyPersonalities
    val riskComfortOptions = OnboardingOptions.riskComforts
    val savingCapacityOptions = OnboardingOptions.savingCapacities

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.SelectFinancialSituation -> {
                _uiState.update { it.copy(financialSituation = event.option) }
            }
            is OnboardingEvent.SelectPrimaryGoal -> {
                _uiState.update { it.copy(primaryGoal = event.option) }
            }
            is OnboardingEvent.SelectMoneyPersonality -> {
                _uiState.update { it.copy(moneyPersonality = event.option) }
            }
            is OnboardingEvent.SelectRiskComfort -> {
                _uiState.update { it.copy(riskComfort = event.option) }
            }
            is OnboardingEvent.SelectSavingCapacity -> {
                _uiState.update { it.copy(savingCapacity = event.option) }
            }
            is OnboardingEvent.PhoneNumberChanged -> {
                _uiState.update { it.copy(phoneNumber = event.phone, error = null) }
            }
            is OnboardingEvent.NextStep -> nextStep()
            is OnboardingEvent.PreviousStep -> previousStep()
            is OnboardingEvent.Submit -> submitOnboarding()
            is OnboardingEvent.ClearError -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun nextStep() {
        val currentStep = _uiState.value.currentStep
        val steps = OnboardingStep.entries
        val currentIndex = steps.indexOf(currentStep)

        if (currentIndex < steps.size - 1) {
            _uiState.update { it.copy(currentStep = steps[currentIndex + 1]) }
        } else {
            submitOnboarding()
        }
    }

    private fun previousStep() {
        val currentStep = _uiState.value.currentStep
        val steps = OnboardingStep.entries
        val currentIndex = steps.indexOf(currentStep)

        if (currentIndex > 0) {
            _uiState.update { it.copy(currentStep = steps[currentIndex - 1]) }
        }
    }

    private fun submitOnboarding() {
        val state = _uiState.value

        if (!state.phoneNumber.isValidPhone()) {
            _uiState.update { it.copy(error = "Please enter a valid 10-digit phone number") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val request = OnboardingRequest(
                financialSituation = state.financialSituation?.apiValue ?: "",
                primaryGoal = state.primaryGoal?.apiValue ?: "",
                moneyPersonality = state.moneyPersonality?.apiValue ?: "",
                riskComfort = state.riskComfort?.apiValue ?: "",
                savingCapacity = state.savingCapacity?.apiValue ?: "",
                phone = state.phoneNumber
            )

            when (val result = onboardingRepository.submitOnboarding(request)) {
                is Resource.Success -> {
                    sessionManager.savePhone(state.phoneNumber)
                    sessionManager.saveHasCompletedOnboarding(true)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isCompleted = true
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
}
