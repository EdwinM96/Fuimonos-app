package com.fuimonos.app.login

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import com.fuimonos.app.commons.Validator
import com.fuimonos.app.data.ILoginRepository
import com.fuimonos.app.data.local.SessionDataPref
import com.fuimonos.app.ext.isValidEmail
import com.fuimonos.app.models.LoginRequest
import com.fuimonos.app.models.LoginResponse

class LoginViewModel(private val loginRepository: ILoginRepository,
                     private val sessionDataPref: SessionDataPref) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val onClearValidations = SingleLiveEvent<Nothing>()
    val onShowValidations = SingleLiveEvent<List<LoginValidation>>()
    val onSignUp = SingleLiveEvent<Nothing>()
    val onForgotPassword = SingleLiveEvent<Nothing>()
    val onLoginSuccess = SingleLiveEvent<Nothing>()

    fun onLogin() {

        onClearValidations.call()

        ifThereAreValidationsDo { validations ->
            onShowValidations.value = validations
            return
        }

        val loginRequest = buildLoginRequest()

        launchCoroutines {

            val apiResult = loginRepository.login(loginRequest)

            apiResult.isSuccessDo { response ->
                handleResponse(response)
                onLoginSuccess.call()
            }

        }

    }

    fun onSignUp() {
        onSignUp.call()
    }

    fun onForgotPassword() {
        onForgotPassword.call()
    }

    private fun handleResponse(response: LoginResponse) {
        sessionDataPref.saveCredentials("${email.value}:${password.value}") //TODO: ENCRIPTAR TAMBIÉN AUNQUE SHAREDPREF YA LO ENCRIPTA
        sessionDataPref.saveNameLogged(response.name ?: "")
        sessionDataPref.saveProfilePhoto(response.profilePhoto ?: "")
        sessionDataPref.saveUserEmail(response.email ?: "")
    }

    private inline fun ifThereAreValidationsDo(block: (List<LoginValidation>) -> Unit) {

        val emailValidations: List<LoginValidation> = Validator.validate(email) {
            checkIf { isNullOrBlank() } set LoginValidation.EMPTY_EMAIL
            checkIf { !isValidEmail() } set LoginValidation.INVALID_EMAIL
        }

        val passValidations: List<LoginValidation> = Validator.validate(password) {
            checkIf { isNullOrBlank() } set LoginValidation.EMTPY_PASSWORD
        }

        val validations = emailValidations + passValidations

        if(validations.isNotEmpty()) {
            block.invoke(validations)
        }

    }

    private fun buildLoginRequest(): LoginRequest {
        val email = email.value ?: ""
        val pass = password.value ?: ""
        return LoginRequest(email, pass)
    }

}
