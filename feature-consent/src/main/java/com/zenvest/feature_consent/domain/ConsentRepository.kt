package com.zenvest.feature_consent.domain

import com.zenvest.core.network.response.Resource
import com.zenvest.feature_consent.data.models.ConsentRequest
import com.zenvest.feature_consent.data.models.ConsentResponse
import com.zenvest.feature_consent.data.models.ConsentStatusResponse
import com.zenvest.feature_consent.data.models.SkipConsentResponse

interface ConsentRepository {
    suspend fun createConsent(request: ConsentRequest): Resource<ConsentResponse>
    suspend fun getConsentStatus(): Resource<ConsentStatusResponse>
    suspend fun skipConsent(): Resource<SkipConsentResponse>
}
