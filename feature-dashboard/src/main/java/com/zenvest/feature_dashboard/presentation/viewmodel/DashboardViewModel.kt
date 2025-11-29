package com.zenvest.feature_dashboard.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenvest.core.common.session.SessionManager
import com.zenvest.core.network.response.Resource
import com.zenvest.feature_dashboard.data.models.AIMessage
import com.zenvest.feature_dashboard.data.models.AIPersona
import com.zenvest.feature_dashboard.data.models.DashboardSummary
import com.zenvest.feature_dashboard.data.models.Goal
import com.zenvest.feature_dashboard.data.models.GoalStatus
import com.zenvest.feature_dashboard.data.models.Investment
import com.zenvest.feature_dashboard.data.models.InvestmentCategory
import com.zenvest.feature_dashboard.data.models.Debt
import com.zenvest.feature_dashboard.data.models.EmergencyFund
import com.zenvest.feature_dashboard.domain.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userName: String = "",
    val summary: DashboardSummary = DashboardSummary(),
    val selectedPersona: AIPersona = AIPersona.BUDGET_COACH,
    val aiMessage: AIMessage? = null
)

sealed interface DashboardEvent {
    data object LoadDashboard : DashboardEvent
    data class SelectPersona(val persona: AIPersona) : DashboardEvent
    data object ClearError : DashboardEvent
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.LoadDashboard -> loadDashboard()
            is DashboardEvent.SelectPersona -> selectPersona(event.persona)
            is DashboardEvent.ClearError -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val userName = sessionManager.getUserName().ifBlank { "User" }
            _uiState.update { it.copy(userName = userName) }

            when (val result = dashboardRepository.getDashboardSummary()) {
                is Resource.Success -> {
                    result.data?.let { summary ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                summary = summary
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    // Use mock data for now
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            summary = getMockSummary()
                        )
                    }
                }
                is Resource.Loading -> { /* Already loading */ }
            }

            selectPersona(_uiState.value.selectedPersona)
        }
    }

    private fun selectPersona(persona: AIPersona) {
        val message = when (persona) {
            AIPersona.BUDGET_COACH -> AIMessage(
                persona = persona,
                message = "Hi! I'm your Budget Coach. I can help you track expenses, create budgets, and find ways to save more.",
                suggestedQuestions = listOf(
                    "How can I reduce my monthly expenses?",
                    "Create a budget for next month",
                    "Where am I spending the most?"
                )
            )
            AIPersona.INVESTMENT_ADVISOR -> AIMessage(
                persona = persona,
                message = "Hello! I'm your Investment Advisor. I can help you with SIP recommendations, portfolio analysis, and investment strategies.",
                suggestedQuestions = listOf(
                    "Which SIP should I start?",
                    "Review my portfolio allocation",
                    "How to increase my returns?"
                )
            )
            AIPersona.DEBT_EXPERT -> AIMessage(
                persona = persona,
                message = "Hi there! I'm your Debt Expert. I can help you create a payoff plan and get debt-free faster.",
                suggestedQuestions = listOf(
                    "What's the best debt payoff strategy?",
                    "How to pay off my loans faster?",
                    "Should I consolidate my debts?"
                )
            )
        }

        _uiState.update {
            it.copy(
                selectedPersona = persona,
                aiMessage = message
            )
        }
    }

    private fun getMockSummary() = DashboardSummary(
        monthlyIncome = 75000.0,
        plannedExpenses = 45000.0,
        emergencyFundProgress = 0.45f,
        totalDebt = 150000.0,
        savingsRate = 0.40f,
        debtStatus = "On Track"
    )
}

// Goals ViewModel
data class GoalsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val goals: List<Goal> = emptyList(),
    val showAddGoalSheet: Boolean = false
)

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoalsUiState())
    val uiState: StateFlow<GoalsUiState> = _uiState.asStateFlow()

    init {
        loadGoals()
    }

    fun loadGoals() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = dashboardRepository.getGoals()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            goals = result.data ?: emptyList()
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            goals = getMockGoals()
                        )
                    }
                }
                is Resource.Loading -> { /* Already loading */ }
            }
        }
    }

    fun showAddGoalSheet(show: Boolean) {
        _uiState.update { it.copy(showAddGoalSheet = show) }
    }

    private fun getMockGoals() = listOf(
        Goal("1", "Emergency Fund", 300000.0, 135000.0, "2025-12-31", GoalStatus.ON_TRACK),
        Goal("2", "New Car", 800000.0, 200000.0, "2026-06-30", GoalStatus.ATTENTION),
        Goal("3", "Vacation", 100000.0, 75000.0, "2025-06-30", GoalStatus.ON_TRACK),
        Goal("4", "Home Down Payment", 2000000.0, 400000.0, "2027-12-31", GoalStatus.BEHIND)
    )
}

// Investments ViewModel
data class InvestmentsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val investments: List<Investment> = emptyList(),
    val totalMonthlyInvestment: Double = 0.0,
    val showAddInvestmentSheet: Boolean = false
)

@HiltViewModel
class InvestmentsViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(InvestmentsUiState())
    val uiState: StateFlow<InvestmentsUiState> = _uiState.asStateFlow()

    init {
        loadInvestments()
    }

    fun loadInvestments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = dashboardRepository.getInvestments()) {
                is Resource.Success -> {
                    val investments = result.data ?: emptyList()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            investments = investments,
                            totalMonthlyInvestment = investments.sumOf { inv -> inv.sipAmount }
                        )
                    }
                }
                is Resource.Error -> {
                    val mockInvestments = getMockInvestments()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            investments = mockInvestments,
                            totalMonthlyInvestment = mockInvestments.sumOf { inv -> inv.sipAmount }
                        )
                    }
                }
                is Resource.Loading -> { /* Already loading */ }
            }
        }
    }

    fun showAddInvestmentSheet(show: Boolean) {
        _uiState.update { it.copy(showAddInvestmentSheet = show) }
    }

    private fun getMockInvestments() = listOf(
        Investment("1", "HDFC Flexi Cap Fund", 5000.0, 5, InvestmentCategory.EQUITY),
        Investment("2", "Axis Bluechip Fund", 3000.0, 10, InvestmentCategory.EQUITY),
        Investment("3", "ICICI Prudential Bond Fund", 2000.0, 15, InvestmentCategory.DEBT),
        Investment("4", "SBI Gold Fund", 1000.0, 1, InvestmentCategory.GOLD)
    )
}

// Debt ViewModel
data class DebtUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val debts: List<Debt> = emptyList(),
    val totalDebt: Double = 0.0,
    val selectedStrategy: com.zenvest.feature_dashboard.data.models.DebtStrategy = com.zenvest.feature_dashboard.data.models.DebtStrategy.AVALANCHE,
    val showAddDebtSheet: Boolean = false
)

@HiltViewModel
class DebtViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DebtUiState())
    val uiState: StateFlow<DebtUiState> = _uiState.asStateFlow()

    init {
        loadDebts()
    }

    fun loadDebts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = dashboardRepository.getDebts()) {
                is Resource.Success -> {
                    val debts = result.data ?: emptyList()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            debts = debts,
                            totalDebt = debts.sumOf { d -> d.balance }
                        )
                    }
                }
                is Resource.Error -> {
                    val mockDebts = getMockDebts()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            debts = mockDebts,
                            totalDebt = mockDebts.sumOf { d -> d.balance }
                        )
                    }
                }
                is Resource.Loading -> { /* Already loading */ }
            }
        }
    }

    fun selectStrategy(strategy: com.zenvest.feature_dashboard.data.models.DebtStrategy) {
        _uiState.update { it.copy(selectedStrategy = strategy) }
    }

    fun showAddDebtSheet(show: Boolean) {
        _uiState.update { it.copy(showAddDebtSheet = show) }
    }

    private fun getMockDebts() = listOf(
        Debt("1", "Credit Card", 50000.0, 36.0, 5000.0, 5),
        Debt("2", "Personal Loan", 100000.0, 14.0, 8000.0, 10),
        Debt("3", "Car Loan", 300000.0, 9.5, 12000.0, 15)
    )
}

// Emergency Fund ViewModel
data class EmergencyFundUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val emergencyFund: EmergencyFund = EmergencyFund(),
    val showAddContributionSheet: Boolean = false
)

@HiltViewModel
class EmergencyFundViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EmergencyFundUiState())
    val uiState: StateFlow<EmergencyFundUiState> = _uiState.asStateFlow()

    init {
        loadEmergencyFund()
    }

    fun loadEmergencyFund() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = dashboardRepository.getEmergencyFund()) {
                is Resource.Success -> {
                    result.data?.let { fund ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                emergencyFund = fund
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            emergencyFund = getMockEmergencyFund()
                        )
                    }
                }
                is Resource.Loading -> { /* Already loading */ }
            }
        }
    }

    fun showAddContributionSheet(show: Boolean) {
        _uiState.update { it.copy(showAddContributionSheet = show) }
    }

    private fun getMockEmergencyFund() = EmergencyFund(
        targetMonths = 6,
        monthlyExpense = 50000.0,
        currentAmount = 135000.0,
        autoTransferEnabled = true,
        autoTransferAmount = 10000.0,
        autoTransferDay = 1
    )
}
