package com.zenvest.feature_onboarding.data.network

import com.zenvest.core.network.response.ApiResponse
import com.zenvest.feature_onboarding.data.models.OnboardingRequest
import com.zenvest.feature_onboarding.data.models.OnboardingResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OnboardingApiService {

    @POST("api/onboarding")
    suspend fun submitOnboarding(@Body request: OnboardingRequest): ApiResponse<OnboardingResponse>
}
