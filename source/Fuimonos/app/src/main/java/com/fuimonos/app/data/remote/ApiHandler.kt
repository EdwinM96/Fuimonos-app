package com.fuimonos.app.data.remote

import com.fuimonos.app.commons.exceptions.BusinessApiException
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class ApiHandler : IApiHandler {

    override suspend fun <T> handleApiCall(call: suspend () -> Response<T>): ApiResult<T> {
        return coroutineScope {
            try {
                val response = withContext(Dispatchers.IO) { call.invoke() }
                handleResponse(response)
            } catch (ex: Exception) {
                handleException(ex)
            }
        }
    }

    private fun <T> handleResponse(response: Response<T>): ApiResult<T> {
        if(!response.isSuccessful) {
            return handleUnsuccessfulResponse(response)
        }
        val responseData = response.body()

        responseData ?: run {
            val apiException = BusinessApiException("Web service is returning empty body response")
            return ApiResult.Error(apiException)
        }

        return ApiResult.Success(responseData)

    }

    private fun <T> handleUnsuccessfulResponse(response: Response<T>): ApiResult.Error {
        val apiException = getBussinessApiExceptionFrom(response)
        return ApiResult.Error(apiException)
    }

    private fun <T> getBussinessApiExceptionFrom(response: Response<T>): BusinessApiException {
        val errorString = getErrorStringFrom(response)

        if(errorString.isNullOrBlank()) {
            return BusinessApiException("Web service is returning empty error body response")
        }


        return BusinessApiException(errorString)
    }

    private fun handleException(exception: Exception): ApiResult.Error {
        return ApiResult.Error(exception)
    }

    private fun <T> getErrorStringFrom(response: Response<T>): String? {
        val gson = Gson()
        val errorBody = response.errorBody()
        errorBody ?: return null
        val stringErrorBody = errorBody.string()
        if(stringErrorBody.isBlank()) { return null }

        val errorString = gson.fromJson(stringErrorBody, String::class.java)
        return errorString
    }

}
