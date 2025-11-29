package com.zenvest.feature_dashboard.data

import com.zenvest.core.network.response.Resource
import com.zenvest.core.network.response.ResponseHandler
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
import com.zenvest.feature_dashboard.data.network.DashboardApiService
import com.zenvest.feature_dashboard.domain.DashboardRepository
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val dashboardApiService: DashboardApiService,
    private val responseHandler: ResponseHandler
) : DashboardRepository {

    override suspend fun getDashboardSummary(): Resource<DashboardSummary> {
        return try {
            val response = dashboardApiService.getDashboardSummary()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getUserProfile(): Resource<UserProfile> {
        return try {
            val response = dashboardApiService.getUserProfile()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getGoals(): Resource<List<Goal>> {
        return try {
            val response = dashboardApiService.getGoals()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun createGoal(request: CreateGoalRequest): Resource<Goal> {
        return try {
            val response = dashboardApiService.createGoal(request)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun deleteGoal(id: String): Resource<Unit> {
        return try {
            val response = dashboardApiService.deleteGoal(id)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getInvestments(): Resource<List<Investment>> {
        return try {
            val response = dashboardApiService.getInvestments()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun createInvestment(request: CreateInvestmentRequest): Resource<Investment> {
        return try {
            val response = dashboardApiService.createInvestment(request)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun deleteInvestment(id: String): Resource<Unit> {
        return try {
            val response = dashboardApiService.deleteInvestment(id)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getDebts(): Resource<List<Debt>> {
        return try {
            val response = dashboardApiService.getDebts()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun createDebt(request: CreateDebtRequest): Resource<Debt> {
        return try {
            val response = dashboardApiService.createDebt(request)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun deleteDebt(id: String): Resource<Unit> {
        return try {
            val response = dashboardApiService.deleteDebt(id)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getEmergencyFund(): Resource<EmergencyFund> {
        return try {
            val response = dashboardApiService.getEmergencyFund()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun addContribution(contribution: Contribution): Resource<Contribution> {
        return try {
            val response = dashboardApiService.addContribution(contribution)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getContributions(): Resource<List<Contribution>> {
        return try {
            val response = dashboardApiService.getContributions()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getTransactionHistory(accountId: String?): Resource<List<Transaction>> {
        return try {
            val response = dashboardApiService.getTransactionHistory(accountId)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getTransactionSummary(accountId: String?): Resource<TransactionSummary> {
        return try {
            val response = dashboardApiService.getTransactionSummary(accountId)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}
