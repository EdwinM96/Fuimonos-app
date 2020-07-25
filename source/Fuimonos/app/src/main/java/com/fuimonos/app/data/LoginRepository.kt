package com.fuimonos.app.data

import com.fuimonos.app.data.remote.ApiResult
import com.fuimonos.app.data.remote.IApiHandler
import com.fuimonos.app.data.remote.ILoginApi
import com.fuimonos.app.models.LoginRequest
import com.fuimonos.app.models.LoginResponse

class LoginRepository(private val apiHandler: IApiHandler,
                      private val loginApi: ILoginApi) : ILoginRepository {

    override suspend fun login(loginRequest: LoginRequest): ApiResult<LoginResponse> {
        return apiHandler.handleApiCall { loginApi.login(loginRequest) }
    }
}