package com.zenvest.feature_auth.domain

import com.zenvest.core.network.response.Resource
import com.zenvest.feature_auth.data.models.AuthResponse
import com.zenvest.feature_auth.data.models.SendOtpResponse

interface AuthRepository {
    suspend fun sendOtp(email: String): Resource<SendOtpResponse>
    suspend fun verifyOtp(email: String, otp: String): Resource<AuthResponse>
    suspend fun googleAuth(idToken: String): Resource<AuthResponse>
}
