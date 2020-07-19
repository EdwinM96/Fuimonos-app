package com.fuimonos.app.recoverpassword

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import com.fuimonos.app.commons.Validator
import com.fuimonos.app.ext.isValidEmail

class RecoverPasswordViewModel : BaseViewModel() {

    val email = MutableLiveData<String>()
    val onClearValidations = SingleLiveEvent<Nothing>()
    val onShowValidations = SingleLiveEvent<List<RecoverPasswordValidation>>()
    val onRecoverPasswordSuccess = SingleLiveEvent<Nothing>()

    fun onRecoverPassword() {
        onClearValidations.call()
        ifThereAreValidationsDo { validations ->
            onShowValidations.value =  validations
            return
        }
        //TODO: CONSUMIR SERVICIO WEB Y REALIZAR VALIDACIONES
        onRecoverPasswordSuccess.call()
    }

    private inline fun ifThereAreValidationsDo(block: (List<RecoverPasswordValidation>) -> Unit) {
        val emailValidations: List<RecoverPasswordValidation> = Validator.validate(email) {
            checkIf { isNullOrBlank() } set RecoverPasswordValidation.EMPTY_EMAIL
            checkIf { !isValidEmail() } set RecoverPasswordValidation.INVALID_EMAIL
        }
        if(emailValidations.isNotEmpty()) {
            block.invoke(emailValidations)
        }
    }

}