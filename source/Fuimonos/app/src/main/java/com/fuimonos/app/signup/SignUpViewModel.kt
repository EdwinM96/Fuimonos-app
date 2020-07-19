package com.fuimonos.app.signup

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import com.fuimonos.app.commons.Validator
import com.fuimonos.app.ext.isValidEmail
import java.io.File

class SignUpViewModel: BaseViewModel() {

    var profilePhotoFile: File? = null
    val name = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val birthday = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val onClearValidations = SingleLiveEvent<Nothing>()
    val onShowValidations = SingleLiveEvent<List<SignUpValidation>>()
    val onPickProfilePhoto = SingleLiveEvent<Nothing>()

    fun onSignUp() {
        onClearValidations.call()
        ifThereAreValidationsDo { validations ->
            onShowValidations.value = validations
            return
        }
        toast.value = "Datos válidos"
    }

    fun onPickProfilePhoto() {
        onPickProfilePhoto.call()
    }

    private inline fun ifThereAreValidationsDo(block: (List<SignUpValidation>) -> Unit) {

        val nameValidations: List<SignUpValidation> = Validator.validate(name) {
            checkIf { isNullOrBlank() } set SignUpValidation.EMTPY_NAME
        }

        val lastNameValidations: List<SignUpValidation> = Validator.validate(lastName) {
            checkIf { isNullOrBlank() } set SignUpValidation.EMTPY_LAST_NAME
        }

        val emailValidations: List<SignUpValidation> = Validator.validate(email) {
            checkIf { isNullOrBlank() } set SignUpValidation.EMPTY_EMAIL
            checkIf { !isValidEmail() } set SignUpValidation.INVALID_EMAIL
        }

        val passwordValidation: List<SignUpValidation> = Validator.validate(password) {
            checkIf { isNullOrBlank() } set SignUpValidation.EMTPY_PASSWORD
        }

        val phoneNumberValidations: List<SignUpValidation> = Validator.validate(phoneNumber) {
            checkIf { isNullOrBlank() } set SignUpValidation.EMPTY_PHONE_NUMBER
        }

        val validations = nameValidations + lastNameValidations +
                          emailValidations + passwordValidation +
                          phoneNumberValidations

        if(validations.isNotEmpty()) {
            block.invoke(validations)
        }

    }

}
