package com.fuimonos.app.data.remote

import retrofit2.Response

interface IApiHandler {
    suspend fun <T> handleApiCall(call: suspend () -> Response<T>) : ApiResult<T>
}
