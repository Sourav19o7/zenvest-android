package com.zenvest.feature_onboarding.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zenvest.core.ui.components.ButtonVariant
import com.zenvest.core.ui.components.ZenvestButton
import com.zenvest.core.ui.components.ZenvestPhoneField
import com.zenvest.core.ui.components.ZenvestSelectableCard
import com.zenvest.core.ui.components.ZenvestStepIndicator
import com.zenvest.feature_onboarding.data.models.OnboardingOption
import com.zenvest.feature_onboarding.data.models.OnboardingStep
import com.zenvest.feature_onboarding.presentation.viewmodel.OnboardingEvent
import com.zenvest.feature_onboarding.presentation.viewmodel.OnboardingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    onNavigateToConsent: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.isCompleted) {
        if (uiState.isCompleted) {
            onNavigateToConsent()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let { error ->
            snackbarHostState.showSnackbar(error)
            viewModel.onEvent(OnboardingEvent.ClearError)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    if (uiState.stepIndex > 0) {
                        IconButton(onClick = { viewModel.onEvent(OnboardingEvent.PreviousStep) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            // Step Indicator
            ZenvestStepIndicator(
                currentStep = uiState.stepIndex,
                totalSteps = uiState.totalSteps,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Step Content
            AnimatedContent(
                targetState = uiState.currentStep,
                transitionSpec = {
                    if (targetState.ordinal > initialState.ordinal) {
                        (slideInHorizontally { it } + fadeIn()) togetherWith
                                (slideOutHorizontally { -it } + fadeOut())
                    } else {
                        (slideInHorizontally { -it } + fadeIn()) togetherWith
                                (slideOutHorizontally { it } + fadeOut())
                    }
                },
                modifier = Modifier.weight(1f),
                label = "step_content"
            ) { step ->
                when (step) {
                    OnboardingStep.FINANCIAL_SITUATION -> {
                        StepContent(
                            title = "What best describes your current financial situation?",
                            options = viewModel.financialSituationOptions,
                            selectedOption = uiState.financialSituation,
                            onSelect = { viewModel.onEvent(OnboardingEvent.SelectFinancialSituation(it)) }
                        )
                    }
                    OnboardingStep.PRIMARY_GOAL -> {
                        StepContent(
                            title = "What's your primary financial goal?",
                            options = viewModel.primaryGoalOptions,
                            selectedOption = uiState.primaryGoal,
                            onSelect = { viewModel.onEvent(OnboardingEvent.SelectPrimaryGoal(it)) }
                        )
                    }
                    OnboardingStep.MONEY_PERSONALITY -> {
                        StepContent(
                            title = "How would you describe your money personality?",
                            options = viewModel.moneyPersonalityOptions,
                            selectedOption = uiState.moneyPersonality,
                            onSelect = { viewModel.onEvent(OnboardingEvent.SelectMoneyPersonality(it)) }
                        )
                    }
                    OnboardingStep.RISK_COMFORT -> {
                        StepContent(
                            title = "What's your comfort level with investment risk?",
                            options = viewModel.riskComfortOptions,
                            selectedOption = uiState.riskComfort,
                            onSelect = { viewModel.onEvent(OnboardingEvent.SelectRiskComfort(it)) }
                        )
                    }
                    OnboardingStep.SAVING_CAPACITY -> {
                        StepContent(
                            title = "How much can you realistically save each month?",
                            options = viewModel.savingCapacityOptions,
                            selectedOption = uiState.savingCapacity,
                            onSelect = { viewModel.onEvent(OnboardingEvent.SelectSavingCapacity(it)) }
                        )
                    }
                    OnboardingStep.PHONE_NUMBER -> {
                        PhoneNumberStep(
                            phoneNumber = uiState.phoneNumber,
                            onPhoneNumberChanged = { viewModel.onEvent(OnboardingEvent.PhoneNumberChanged(it)) }
                        )
                    }
                }
            }

            // Bottom Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (uiState.stepIndex > 0) {
                    ZenvestButton(
                        text = "Back",
                        onClick = { viewModel.onEvent(OnboardingEvent.PreviousStep) },
                        variant = ButtonVariant.Outline,
                        modifier = Modifier.weight(1f),
                        enabled = !uiState.isLoading
                    )
                }
                ZenvestButton(
                    text = if (uiState.currentStep == OnboardingStep.PHONE_NUMBER) "Complete" else "Continue",
                    onClick = { viewModel.onEvent(OnboardingEvent.NextStep) },
                    modifier = Modifier.weight(1f),
                    enabled = uiState.canProceed && !uiState.isLoading,
                    isLoading = uiState.isLoading
                )
            }
        }
    }
}

@Composable
private fun StepContent(
    title: String,
    options: List<OnboardingOption>,
    selectedOption: OnboardingOption?,
    onSelect: (OnboardingOption) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(options) { option ->
            ZenvestSelectableCard(
                title = option.title,
                description = option.description,
                isSelected = selectedOption?.id == option.id,
                onClick = { onSelect(option) }
            )
        }
    }
}

@Composable
private fun PhoneNumberStep(
    phoneNumber: String,
    onPhoneNumberChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Enter your phone number",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "We'll use this to send you important updates about your finances",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        ZenvestPhoneField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChanged,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
