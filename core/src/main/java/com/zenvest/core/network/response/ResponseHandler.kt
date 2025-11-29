package com.zenvest.core.network.response

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseHandler @Inject constructor() {

    fun <T> handleSuccess(response: ApiResponse<T>): Resource<T> {
        return if (response.success && response.data != null) {
            Resource.success(response.data)
        } else {
            val errorMessage = response.error?.message
                ?: response.message
                ?: "Unknown error occurred"
            Resource.error(errorMessage)
        }
    }

    fun <T> handleException(exception: Exception): Resource<T> {
        return when (exception) {
            is HttpException -> {
                val code = exception.code()
                val message = when (code) {
                    400 -> "Bad request"
                    401 -> "Unauthorized. Please login again."
                    403 -> "Forbidden. You don't have permission."
                    404 -> "Resource not found"
                    409 -> "Conflict. Resource already exists."
                    500 -> "Internal server error"
                    else -> "Network error: ${exception.message()}"
                }
                Resource.error(message, code)
            }
            is SocketTimeoutException -> {
                Resource.error("Connection timed out. Please try again.")
            }
            is IOException -> {
                Resource.error("No internet connection. Please check your network.")
            }
            else -> {
                Resource.error(exception.message ?: "An unexpected error occurred")
            }
        }
    }
}
