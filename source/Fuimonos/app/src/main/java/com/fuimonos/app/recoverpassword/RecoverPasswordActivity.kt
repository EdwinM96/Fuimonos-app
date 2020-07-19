package com.fuimonos.app.recoverpassword

import android.widget.Toast
import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.databinding.ActRecoverPasswordBinding
import com.fuimonos.app.recoverpassword.RecoverPasswordValidation.*
import kotlinx.android.synthetic.main.act_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecoverPasswordActivity : BindableVMActivity<RecoverPasswordViewModel, ActRecoverPasswordBinding>() {

    override val mViewModel: RecoverPasswordViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.act_recover_password

    override fun setupSubscription() {
        super.setupSubscription()
        mViewModel.onClearValidations.observe(this, Observer {
            clearValidations()
        })
        mViewModel.onShowValidations.observe(this, Observer { validations ->
            showValidations(validations)
        })
        mViewModel.onRecoverPasswordSuccess.observe(this, Observer {
            showSuccessMessage()
        })
    }

    private fun clearValidations() {
        inputEmail.error = null
    }

    private fun showValidations(validations: List<RecoverPasswordValidation>) {
        validations.forEach { validation ->
            when(validation) {
                EMPTY_EMAIL   -> inputEmail.error = getString(R.string.val_empty_email)
                INVALID_EMAIL -> inputEmail.error = getString(R.string.val_invalid_email)
            }
        }
    }

    private fun showSuccessMessage() {
        //TODO: PRESENTAR PANTALLA DE LISTO
        Toast.makeText(this, "Revisar correo", Toast.LENGTH_LONG).show()
    }

}
