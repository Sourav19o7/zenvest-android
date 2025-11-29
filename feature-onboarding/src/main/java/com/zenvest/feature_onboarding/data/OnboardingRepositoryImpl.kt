package com.zenvest.feature_onboarding.data

import android.util.Log
import com.zenvest.core.network.response.Resource
import com.zenvest.core.network.response.ResponseHandler
import com.zenvest.feature_onboarding.data.models.OnboardingRequest
import com.zenvest.feature_onboarding.data.models.OnboardingResponse
import com.zenvest.feature_onboarding.data.network.OnboardingApiService
import com.zenvest.feature_onboarding.domain.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingApiService: OnboardingApiService,
    private val responseHandler: ResponseHandler
) : OnboardingRepository {

    override suspend fun submitOnboarding(request: OnboardingRequest): Resource<OnboardingResponse> {
        return try {
            val response = onboardingApiService.submitOnboarding(request)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            Log.i(
                "OnboardingRepositoryImpl",
                "submitOnboarding: ${e}"
            )
            responseHandler.handleException(e)
        }
    }
}
