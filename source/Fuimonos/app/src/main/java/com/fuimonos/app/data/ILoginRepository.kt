package com.fuimonos.app.data

import com.fuimonos.app.data.remote.ApiResult
import com.fuimonos.app.models.LoginRequest
import com.fuimonos.app.models.LoginResponse

interface ILoginRepository {
    suspend fun login(loginRequest: LoginRequest) : ApiResult<LoginResponse>
}
