package com.zenvest.feature_dashboard.data.network

import com.zenvest.core.network.response.ApiResponse
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
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DashboardApiService {

    // Dashboard
    @GET("api/dashboard/summary")
    suspend fun getDashboardSummary(): ApiResponse<DashboardSummary>

    // Profile
    @GET("api/user/profile")
    suspend fun getUserProfile(): ApiResponse<UserProfile>

    // Goals
    @GET("api/goals")
    suspend fun getGoals(): ApiResponse<List<Goal>>

    @POST("api/goals")
    suspend fun createGoal(@Body request: CreateGoalRequest): ApiResponse<Goal>

    @PUT("api/goals/{id}")
    suspend fun updateGoal(@Path("id") id: String, @Body request: CreateGoalRequest): ApiResponse<Goal>

    @DELETE("api/goals/{id}")
    suspend fun deleteGoal(@Path("id") id: String): ApiResponse<Unit>

    // Investments
    @GET("api/investments")
    suspend fun getInvestments(): ApiResponse<List<Investment>>

    @POST("api/investments")
    suspend fun createInvestment(@Body request: CreateInvestmentRequest): ApiResponse<Investment>

    @DELETE("api/investments/{id}")
    suspend fun deleteInvestment(@Path("id") id: String): ApiResponse<Unit>

    // Debt
    @GET("api/debts")
    suspend fun getDebts(): ApiResponse<List<Debt>>

    @POST("api/debts")
    suspend fun createDebt(@Body request: CreateDebtRequest): ApiResponse<Debt>

    @DELETE("api/debts/{id}")
    suspend fun deleteDebt(@Path("id") id: String): ApiResponse<Unit>

    // Emergency Fund
    @GET("api/emergency-fund")
    suspend fun getEmergencyFund(): ApiResponse<EmergencyFund>

    @POST("api/emergency-fund/contribution")
    suspend fun addContribution(@Body contribution: Contribution): ApiResponse<Contribution>

    @GET("api/emergency-fund/contributions")
    suspend fun getContributions(): ApiResponse<List<Contribution>>

    // Transactions
    @GET("api/transactions/history")
    suspend fun getTransactionHistory(@Query("account_id") accountId: String? = null): ApiResponse<List<Transaction>>

    @GET("api/transactions/summary")
    suspend fun getTransactionSummary(@Query("account_id") accountId: String? = null): ApiResponse<TransactionSummary>
}
