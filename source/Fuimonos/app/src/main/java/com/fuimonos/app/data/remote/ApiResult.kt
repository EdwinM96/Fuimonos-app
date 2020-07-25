package com.fuimonos.app.data.remote

import java.lang.Exception

sealed class ApiResult<out T> {
    class Success<out T>(val response: T) : ApiResult<T>()
    class Error(val exceptions: Exception) : ApiResult<Nothing>()
}
