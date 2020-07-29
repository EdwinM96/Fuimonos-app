package com.fuimonos.app.changepassword

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.changepassword.ChangePasswordValidation.*
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import com.fuimonos.app.data.ILoginRepository
import com.fuimonos.app.ext.validate
import com.fuimonos.app.models.ChangePasswordRequest
import com.fuimonos.app.models.ChangePasswordResponse
import timber.log.Timber

class ChangePasswordViewModel(private val loginRepository: ILoginRepository) : BaseViewModel() {

    val currentPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    val onClearValidations = SingleLiveEvent<Nothing>()
    val onShowValidations = SingleLiveEvent<List<ChangePasswordValidation>>()
    val onChangingPasswordSuccess = SingleLiveEvent<Nothing>()

    fun onChangePassword() {

        onClearValidations.call()

        ifThereAreValidationsDo {  validations ->
            onShowValidations.value = validations
            return
        }

        val changePasswordRequest = buildChangePasswordRequest()

        launchCoroutines {

            val apiResult = loginRepository.changePassword(changePasswordRequest)

            apiResult.isSuccessDo {  response ->
                handleResponse(response)
                onChangingPasswordSuccess.call()
            }

        }

    }

    private fun handleResponse(response: ChangePasswordResponse) {
        Timber.d(response.message)
    }

    private inline fun ifThereAreValidationsDo(block: (List<ChangePasswordValidation>) -> Unit) {

        val currentPasswordVal: List<ChangePasswordValidation> = currentPassword.validate {
            checkIf { isNullOrBlank() } set EMPTY_CURRENT_PASS
        }
        val newPasswordVal: List<ChangePasswordValidation> = newPassword.validate {
            checkIf { isNullOrBlank() } set EMPTY_NEW_PASS
        }
        val confirmPasswordVal: List<ChangePasswordValidation> = confirmPassword.validate {
            checkIf { isNullOrBlank() } set EMPTY_CONFIRM_PASS
            checkIf { this != newPassword.value } set PASS_NOT_EQUALS
        }

        val validations = mutableListOf<ChangePasswordValidation>()

        validations.addAll( currentPasswordVal + newPasswordVal + confirmPasswordVal)

        if(validations.contains(PASS_NOT_EQUALS)) {
            validations.remove(EMPTY_NEW_PASS)
        }

        if(validations.isNotEmpty()) {
            block.invoke(validations)
        }

    }

    private fun buildChangePasswordRequest(): ChangePasswordRequest {
        val currentPassword = currentPassword.value ?: ""
        val newPassword = newPassword.value ?: ""
        val confirmPassword = confirmPassword.value ?: ""
        return ChangePasswordRequest(currentPassword, newPassword, confirmPassword)
    }

}
