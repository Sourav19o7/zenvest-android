package com.zenvest.feature_onboarding.data.models

import com.google.gson.annotations.SerializedName

// Request model
data class OnboardingRequest(
    @SerializedName("financial_situation")
    val financialSituation: String,
    @SerializedName("primary_goal")
    val primaryGoal: String,
    @SerializedName("money_personality")
    val moneyPersonality: String,
    @SerializedName("risk_comfort")
    val riskComfort: String,
    @SerializedName("saving_capacity")
    val savingCapacity: String,
    @SerializedName("phone")
    val phone: String
)

// Response model
data class OnboardingResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String? = null
)

// UI models
data class OnboardingOption(
    val id: String,
    val title: String,
    val description: String? = null,
    val apiValue: String
)

enum class OnboardingStep {
    FINANCIAL_SITUATION,
    PRIMARY_GOAL,
    MONEY_PERSONALITY,
    RISK_COMFORT,
    SAVING_CAPACITY,
    PHONE_NUMBER
}

object OnboardingOptions {
    val financialSituations = listOf(
        OnboardingOption(
            id = "just_getting_started",
            title = "Just getting started",
            description = "I'm new to managing my finances",
            apiValue = "just_getting_started"
        ),
        OnboardingOption(
            id = "stable_but_stretched",
            title = "Stable but stretched",
            description = "I have income but struggle to save",
            apiValue = "stable_but_stretched"
        ),
        OnboardingOption(
            id = "comfortable",
            title = "Comfortable",
            description = "I'm managing well and want to grow",
            apiValue = "comfortable"
        ),
        OnboardingOption(
            id = "strong_foundation",
            title = "Strong foundation",
            description = "I have solid savings and investments",
            apiValue = "strong_foundation"
        )
    )

    val primaryGoals = listOf(
        OnboardingOption(
            id = "emergency_fund",
            title = "Build an emergency fund",
            description = "Save for unexpected expenses",
            apiValue = "emergency_fund"
        ),
        OnboardingOption(
            id = "pay_off_debt",
            title = "Pay off debt",
            description = "Eliminate loans and credit card debt",
            apiValue = "pay_off_debt"
        ),
        OnboardingOption(
            id = "save_for_specific",
            title = "Save for a specific goal",
            description = "Home, car, education, or vacation",
            apiValue = "save_for_specific"
        ),
        OnboardingOption(
            id = "grow_wealth",
            title = "Grow my wealth",
            description = "Invest and build long-term wealth",
            apiValue = "grow_wealth"
        )
    )

    val moneyPersonalities = listOf(
        OnboardingOption(
            id = "saver",
            title = "Saver",
            description = "I prioritize saving over spending",
            apiValue = "saver"
        ),
        OnboardingOption(
            id = "spender",
            title = "Spender",
            description = "I enjoy spending on experiences and things",
            apiValue = "spender"
        ),
        OnboardingOption(
            id = "avoider",
            title = "Avoider",
            description = "I prefer not to think about money",
            apiValue = "avoider"
        ),
        OnboardingOption(
            id = "money_monk",
            title = "Money Monk",
            description = "I believe money isn't important",
            apiValue = "money_monk"
        ),
        OnboardingOption(
            id = "amasser",
            title = "Amasser",
            description = "I feel happiest when I have money saved",
            apiValue = "amasser"
        )
    )

    val riskComforts = listOf(
        OnboardingOption(
            id = "conservative",
            title = "Keep it safe",
            description = "I prefer low-risk investments",
            apiValue = "conservative"
        ),
        OnboardingOption(
            id = "balanced",
            title = "Balanced",
            description = "Mix of growth and stability",
            apiValue = "balanced"
        ),
        OnboardingOption(
            id = "aggressive",
            title = "High growth",
            description = "I'm comfortable with higher risk for higher returns",
            apiValue = "aggressive"
        )
    )

    val savingCapacities = listOf(
        OnboardingOption(
            id = "under_5k",
            title = "Less than ₹5,000",
            description = "Starting small",
            apiValue = "under_5k"
        ),
        OnboardingOption(
            id = "5k_to_15k",
            title = "₹5,000 - ₹15,000",
            description = "Building momentum",
            apiValue = "5k_to_15k"
        ),
        OnboardingOption(
            id = "15k_to_30k",
            title = "₹15,000 - ₹30,000",
            description = "Steady growth",
            apiValue = "15k_to_30k"
        ),
        OnboardingOption(
            id = "above_30k",
            title = "More than ₹30,000",
            description = "Strong saving potential",
            apiValue = "above_30k"
        )
    )
}
