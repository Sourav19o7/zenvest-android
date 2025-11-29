package com.zenvest.feature_auth.data.models

import com.google.gson.annotations.SerializedName

// Request models
data class SendOtpRequest(
    @SerializedName("email")
    val email: String
)

data class VerifyOtpRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("otp")
    val otp: String
)

data class GoogleAuthRequest(
    @SerializedName("idToken")
    val idToken: String
)

// Response models
data class AuthResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("isNewUser")
    val isNewUser: Boolean,
    @SerializedName("hasCompletedConsent")
    val hasCompletedConsent: Boolean
)

data class SendOtpResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("expiresIn")
    val expiresIn: Int? = null
)
