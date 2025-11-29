package com.zenvest.feature_consent.data

import android.util.Log
import com.zenvest.core.network.response.Resource
import com.zenvest.core.network.response.ResponseHandler
import com.zenvest.feature_consent.data.models.ConsentRequest
import com.zenvest.feature_consent.data.models.ConsentResponse
import com.zenvest.feature_consent.data.models.ConsentStatusResponse
import com.zenvest.feature_consent.data.models.SkipConsentResponse
import com.zenvest.feature_consent.data.network.ConsentApiService
import com.zenvest.feature_consent.domain.ConsentRepository
import javax.inject.Inject

class ConsentRepositoryImpl @Inject constructor(
    private val consentApiService: ConsentApiService,
    private val responseHandler: ResponseHandler
) : ConsentRepository {

    override suspend fun createConsent(request: ConsentRequest): Resource<ConsentResponse> {
        return try {
            val response = consentApiService.createConsent(request)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            Log.i(
                "ConsentRepositoryImpl",
                "createConsent: ${e}"
            )
            responseHandler.handleException(e)
        }
    }

    override suspend fun getConsentStatus(): Resource<ConsentStatusResponse> {
        return try {
            val response = consentApiService.getConsentStatus()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun skipConsent(): Resource<SkipConsentResponse> {
        return try {
            val response = consentApiService.skipConsent(emptyMap<String, String>())
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}
