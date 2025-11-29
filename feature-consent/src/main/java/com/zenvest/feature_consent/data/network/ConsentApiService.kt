package com.zenvest.feature_consent.data.network

import com.zenvest.core.network.response.ApiResponse
import com.zenvest.feature_consent.data.models.ConsentRequest
import com.zenvest.feature_consent.data.models.ConsentResponse
import com.zenvest.feature_consent.data.models.ConsentStatusResponse
import com.zenvest.feature_consent.data.models.SkipConsentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ConsentApiService {

    @POST("api/consent/create")
    suspend fun createConsent(@Body request: ConsentRequest): ApiResponse<ConsentResponse>

    @GET("api/consent/get")
    suspend fun getConsentStatus(): ApiResponse<ConsentStatusResponse>

    @POST("api/consent/skip")
    suspend fun skipConsent(@Body request: Map<String, String>): ApiResponse<SkipConsentResponse>
}
