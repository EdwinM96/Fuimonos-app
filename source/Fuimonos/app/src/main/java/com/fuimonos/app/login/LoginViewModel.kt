package com.fuimonos.app.login

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import com.fuimonos.app.commons.Validator
import com.fuimonos.app.ext.isValidEmail

class LoginViewModel : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val onClearValidations = SingleLiveEvent<Nothing>()
    val onShowValidations = SingleLiveEvent<List<LoginValidation>>()
    val onLoginSucess = SingleLiveEvent<Nothing>()

    fun onLogin() {

        onClearValidations.call()

        ifThereAreValidationsDo { validations ->
            onShowValidations.value = validations
            return
        }

        onLoginSucess.call()

    }

    fun onForgotPassword() {
        toast.value = "En construcción"
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

}
