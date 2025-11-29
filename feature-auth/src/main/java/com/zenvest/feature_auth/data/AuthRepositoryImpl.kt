package com.zenvest.feature_auth.data

import android.util.Log
import com.zenvest.core.network.response.Resource
import com.zenvest.core.network.response.ResponseHandler
import com.zenvest.feature_auth.data.models.AuthResponse
import com.zenvest.feature_auth.data.models.GoogleAuthRequest
import com.zenvest.feature_auth.data.models.SendOtpRequest
import com.zenvest.feature_auth.data.models.SendOtpResponse
import com.zenvest.feature_auth.data.models.VerifyOtpRequest
import com.zenvest.feature_auth.data.network.AuthApiService
import com.zenvest.feature_auth.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val responseHandler: ResponseHandler
) : AuthRepository {

    override suspend fun sendOtp(email: String): Resource<SendOtpResponse> {
        return try {
            val response = authApiService.sendOtp(SendOtpRequest(email))
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            Log.i(
                "AuthRepositoryImpl",
                "sendOtp: ${e}"
            )
            responseHandler.handleException(e)
        }
    }

    override suspend fun verifyOtp(email: String, otp: String): Resource<AuthResponse> {
        return try {
            val response = authApiService.verifyOtp(VerifyOtpRequest(email, otp))
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun googleAuth(idToken: String): Resource<AuthResponse> {
        return try {
            val response = authApiService.googleAuth(GoogleAuthRequest(idToken))
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}
