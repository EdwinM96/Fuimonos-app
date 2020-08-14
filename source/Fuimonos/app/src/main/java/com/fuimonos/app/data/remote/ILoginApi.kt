package com.fuimonos.app.data.remote

import com.fuimonos.app.models.ChangePasswordRequest
import com.fuimonos.app.models.ChangePasswordResponse
import com.fuimonos.app.models.LoginRequest
import com.fuimonos.app.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("change_password")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Response<ChangePasswordResponse>

}
