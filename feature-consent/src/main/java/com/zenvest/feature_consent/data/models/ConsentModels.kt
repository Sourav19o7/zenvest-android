package com.zenvest.feature_consent.data.models

import com.google.gson.annotations.SerializedName

// Request models
data class ConsentRequest(
    @SerializedName("consentDuration")
    val consentDuration: ConsentDuration,
    @SerializedName("vua")
    val vua: String,
    @SerializedName("dataRange")
    val dataRange: DataRange,
    @SerializedName("context")
    val context: List<Any> = emptyList(),
    @SerializedName("additionalParams")
    val additionalParams: AdditionalParams = AdditionalParams()
)

data class ConsentDuration(
    @SerializedName("unit")
    val unit: String = "MONTH",
    @SerializedName("value")
    val value: String = "12"
)

data class DataRange(
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String
)

data class AdditionalParams(
    @SerializedName("tags")
    val tags: List<String> = listOf("zenvest-app")
)

// Response models
data class ConsentResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("redirectUrl")
    val redirectUrl: String? = null
)

data class ConsentStatusResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("status")
    val status: String
)

data class SkipConsentResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String? = null
)
