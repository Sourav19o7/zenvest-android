package com.zenvest.core.network.config

data class NetworkConfig(
    val baseUrl: String,
    val isDebug: Boolean = false,
    val connectTimeout: Long = 30,
    val readTimeout: Long = 30,
    val writeTimeout: Long = 30
)
