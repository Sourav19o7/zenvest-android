package com.zenvest.feature_auth.data.network

import com.zenvest.core.network.response.ApiResponse
import com.zenvest.feature_auth.data.models.AuthResponse
import com.zenvest.feature_auth.data.models.GoogleAuthRequest
import com.zenvest.feature_auth.data.models.SendOtpRequest
import com.zenvest.feature_auth.data.models.SendOtpResponse
import com.zenvest.feature_auth.data.models.VerifyOtpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/auth/send/otp")
    suspend fun sendOtp(@Body request: SendOtpRequest): ApiResponse<SendOtpResponse>

    @POST("api/auth/verify/otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): ApiResponse<AuthResponse>

    @POST("api/auth/google")
    suspend fun googleAuth(@Body request: GoogleAuthRequest): ApiResponse<AuthResponse>
}
