package com.zenvest.feature_dashboard.data.models

import com.google.gson.annotations.SerializedName

// User Data
data class UserProfile(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val phone: String = "",
    val subscriptionStatus: SubscriptionStatus = SubscriptionStatus.INACTIVE
)

enum class SubscriptionStatus {
    ACTIVE, INACTIVE, TRIAL
}

// Dashboard Summary
data class DashboardSummary(
    val monthlyIncome: Double = 0.0,
    val plannedExpenses: Double = 0.0,
    val emergencyFundProgress: Float = 0f,
    val totalDebt: Double = 0.0,
    val savingsRate: Float = 0f,
    val debtStatus: String = "On Track"
)

// Goals
data class Goal(
    val id: String,
    val name: String,
    val targetAmount: Double,
    val currentAmount: Double,
    val targetDate: String,
    val status: GoalStatus = GoalStatus.ON_TRACK
) {
    val progress: Float
        get() = if (targetAmount > 0) (currentAmount / targetAmount).toFloat().coerceIn(0f, 1f) else 0f
}

enum class GoalStatus {
    ON_TRACK, ATTENTION, BEHIND
}

data class CreateGoalRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("target_amount")
    val targetAmount: Double,
    @SerializedName("target_date")
    val targetDate: String
)

// Investments
data class Investment(
    val id: String,
    val fundName: String,
    val sipAmount: Double,
    val debitDay: Int,
    val category: InvestmentCategory = InvestmentCategory.EQUITY
)

enum class InvestmentCategory {
    EQUITY, DEBT, HYBRID, GOLD, OTHER
}

data class AssetMix(
    val equity: Float = 0.6f,
    val debt: Float = 0.3f,
    val cash: Float = 0.1f
)

data class CreateInvestmentRequest(
    @SerializedName("fund_name")
    val fundName: String,
    @SerializedName("sip_amount")
    val sipAmount: Double,
    @SerializedName("debit_day")
    val debitDay: Int,
    @SerializedName("category")
    val category: String
)

// Debt
data class Debt(
    val id: String,
    val name: String,
    val balance: Double,
    val interestRate: Double,
    val minimumPayment: Double,
    val dueDay: Int
)

enum class DebtStrategy {
    SNOWBALL, // Pay smallest balance first
    AVALANCHE // Pay highest interest rate first
}

data class CreateDebtRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("interest_rate")
    val interestRate: Double,
    @SerializedName("minimum_payment")
    val minimumPayment: Double,
    @SerializedName("due_day")
    val dueDay: Int
)

// Emergency Fund
data class EmergencyFund(
    val targetMonths: Int = 6,
    val monthlyExpense: Double = 0.0,
    val currentAmount: Double = 0.0,
    val autoTransferEnabled: Boolean = false,
    val autoTransferAmount: Double = 0.0,
    val autoTransferDay: Int = 1
) {
    val targetAmount: Double
        get() = targetMonths * monthlyExpense

    val progress: Float
        get() = if (targetAmount > 0) (currentAmount / targetAmount).toFloat().coerceIn(0f, 1f) else 0f
}

data class Contribution(
    val id: String,
    val amount: Double,
    val date: String,
    val note: String = ""
)

// Transactions
data class Transaction(
    val id: String,
    val amount: Double,
    val type: TransactionType,
    val mode: String,
    val narration: String,
    val timestamp: String,
    val balance: Double
)

enum class TransactionType {
    CREDIT, DEBIT, OPENING, INSTALLMENT, INTEREST, TDS, OTHERS
}

data class TransactionSummary(
    val summary: String,
    val totalTransactions: Int,
    val totalCredits: Double,
    val totalDebits: Double,
    val insights: List<String>,
    val suggestions: List<String>
)

// AI Personas
enum class AIPersona {
    BUDGET_COACH,
    INVESTMENT_ADVISOR,
    DEBT_EXPERT
}

data class AIMessage(
    val persona: AIPersona,
    val message: String,
    val suggestedQuestions: List<String>
)

// Settings
data class AppSettings(
    val language: String = "en",
    val theme: AppTheme = AppTheme.SYSTEM,
    val billReminders: Boolean = true,
    val goalMilestones: Boolean = true,
    val investmentSuggestions: Boolean = true
)

enum class AppTheme {
    LIGHT, DARK, SYSTEM
}

enum class Language(val code: String, val displayName: String) {
    ENGLISH("en", "English"),
    HINDI("hi", "Hindi"),
    TAMIL("ta", "Tamil"),
    TELUGU("te", "Telugu"),
    MARATHI("mr", "Marathi"),
    BENGALI("bn", "Bengali")
}
