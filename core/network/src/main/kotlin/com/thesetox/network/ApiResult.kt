package com.thesetox.network

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()

    data class Error(val message: String, val cause: Throwable? = null) : ApiResult<Nothing>()
}
