package com.zenvest.core.network.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: T?,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("error")
    val error: ApiError? = null
)

data class ApiError(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("details")
    val details: Map<String, Any>? = null
)
