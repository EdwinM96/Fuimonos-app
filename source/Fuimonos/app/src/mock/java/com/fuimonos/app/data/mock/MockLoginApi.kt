package com.fuimonos.app.data.mock

import com.fuimonos.app.data.remote.ILoginApi
import com.fuimonos.app.models.ChangePasswordRequest
import com.fuimonos.app.models.ChangePasswordResponse
import com.fuimonos.app.models.LoginRequest
import com.fuimonos.app.models.LoginResponse
import kotlinx.coroutines.delay
import retrofit2.Response

class MockLoginApi : ILoginApi {

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {

        //Api latency simulation
        delay(1000)

        val responseData = LoginResponse(
            MockHelper.buildGoogleDriveImageUrlWith("1gaqPQocSKF_Jf0lxVKF5T56026BM2RsO"),
            "Rafael Alegría",
            "rafalesan96@gmail.com")

        return Response.success(responseData)

    }

    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): Response<ChangePasswordResponse> {
        delay(1000)
        val responseData = ChangePasswordResponse("Hemos actualizado tu contraseña")
        return Response.success(responseData)
    }

}
