package com.zenvest.core.network.response

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String, val code: Int? = null) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    val isLoading: Boolean get() = this is Loading

    fun <R> map(transform: (T) -> R): Resource<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> this
        is Loading -> this
    }

    fun getOrNull(): T? = (this as? Success)?.data


    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun error(message: String, code: Int? = null): Resource<Nothing> = Error(message, code)
        fun loading(): Resource<Nothing> = Loading
    }
}
