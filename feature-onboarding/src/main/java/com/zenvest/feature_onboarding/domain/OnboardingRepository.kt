package com.zenvest.feature_onboarding.domain

import com.zenvest.core.network.response.Resource
import com.zenvest.feature_onboarding.data.models.OnboardingRequest
import com.zenvest.feature_onboarding.data.models.OnboardingResponse

interface OnboardingRepository {
    suspend fun submitOnboarding(request: OnboardingRequest): Resource<OnboardingResponse>
}
