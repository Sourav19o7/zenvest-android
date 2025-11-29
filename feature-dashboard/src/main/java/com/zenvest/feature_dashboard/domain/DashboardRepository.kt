package com.zenvest.feature_dashboard.domain

import com.zenvest.core.network.response.Resource
import com.zenvest.feature_dashboard.data.models.Contribution
import com.zenvest.feature_dashboard.data.models.CreateDebtRequest
import com.zenvest.feature_dashboard.data.models.CreateGoalRequest
import com.zenvest.feature_dashboard.data.models.CreateInvestmentRequest
import com.zenvest.feature_dashboard.data.models.DashboardSummary
import com.zenvest.feature_dashboard.data.models.Debt
import com.zenvest.feature_dashboard.data.models.EmergencyFund
import com.zenvest.feature_dashboard.data.models.Goal
import com.zenvest.feature_dashboard.data.models.Investment
import com.zenvest.feature_dashboard.data.models.Transaction
import com.zenvest.feature_dashboard.data.models.TransactionSummary
import com.zenvest.feature_dashboard.data.models.UserProfile

interface DashboardRepository {
    // Dashboard
    suspend fun getDashboardSummary(): Resource<DashboardSummary>

    // Profile
    suspend fun getUserProfile(): Resource<UserProfile>

    // Goals
    suspend fun getGoals(): Resource<List<Goal>>
    suspend fun createGoal(request: CreateGoalRequest): Resource<Goal>
    suspend fun deleteGoal(id: String): Resource<Unit>

    // Investments
    suspend fun getInvestments(): Resource<List<Investment>>
    suspend fun createInvestment(request: CreateInvestmentRequest): Resource<Investment>
    suspend fun deleteInvestment(id: String): Resource<Unit>

    // Debt
    suspend fun getDebts(): Resource<List<Debt>>
    suspend fun createDebt(request: CreateDebtRequest): Resource<Debt>
    suspend fun deleteDebt(id: String): Resource<Unit>

    // Emergency Fund
    suspend fun getEmergencyFund(): Resource<EmergencyFund>
    suspend fun addContribution(contribution: Contribution): Resource<Contribution>
    suspend fun getContributions(): Resource<List<Contribution>>

    // Transactions
    suspend fun getTransactionHistory(accountId: String? = null): Resource<List<Transaction>>
    suspend fun getTransactionSummary(accountId: String? = null): Resource<TransactionSummary>
}
