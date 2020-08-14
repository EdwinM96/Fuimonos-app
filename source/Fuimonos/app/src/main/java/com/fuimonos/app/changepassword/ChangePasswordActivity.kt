package com.fuimonos.app.changepassword

import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.actionmessage.ActionMessage
import com.fuimonos.app.actionmessage.ActionMessageActivity
import com.fuimonos.app.changepassword.ChangePasswordValidation.*
import com.fuimonos.app.commons.BackViewModelActivity
import com.fuimonos.app.databinding.ActChangePasswordBinding
import com.fuimonos.app.login.LoginActivity
import kotlinx.android.synthetic.main.act_change_password.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : BackViewModelActivity<ChangePasswordViewModel, ActChangePasswordBinding>() {

    override val mViewModel: ChangePasswordViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.act_change_password

    override fun setupSubscription() {
        super.setupSubscription()
        mViewModel.onClearValidations.observe(this, Observer {
            clearValidations()
        })
        mViewModel.onShowValidations.observe(this, Observer { validations ->
            showValidations(validations)
        })
        mViewModel.onChangingPasswordSuccess.observe(this, Observer {
            showReadyMessage()
        })
    }

    private fun showReadyMessage() {
        val actionMessage = ActionMessage("¡Listo y seguro!",
                                          R.drawable.ic_ready_secure,
                                          "¡Listo!",
                                          "Hemos actualizado tu contraseña",
                                          getString(R.string.log_in),
                                          clearBackStack = true)
        ActionMessageActivity.launchWith(this, actionMessage, LoginActivity::class.java)
    }

    private fun clearValidations() {
        inputCurrentPassword.error = null
        inputNewPassword.error = null
        inputConfirmPassword.error = null
    }

    private fun showValidations(validations: List<ChangePasswordValidation>) {
        validations.forEach { validation ->
            when(validation) {
                EMPTY_CURRENT_PASS -> inputCurrentPassword.error = getString(R.string.val_empty_current_pass)
                EMPTY_NEW_PASS     -> inputNewPassword.error = getString(R.string.val_empty_new_pass)
                EMPTY_CONFIRM_PASS -> inputConfirmPassword.error = getString(R.string.val_empty_confirm_pass)
                PASS_NOT_EQUALS    -> {
                    inputNewPassword.error = getString(R.string.val_pass_not_equals)
                    inputConfirmPassword.error = getString(R.string.val_pass_not_equals)
                }
            }
        }
    }

}