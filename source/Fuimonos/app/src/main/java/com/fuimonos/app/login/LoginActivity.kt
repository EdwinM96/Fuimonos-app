package com.fuimonos.app.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.databinding.ActLoginBinding
import com.fuimonos.app.login.LoginValidation.*
import com.fuimonos.app.signup.SignUpActivity
import kotlinx.android.synthetic.main.act_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BindableVMActivity<LoginViewModel, ActLoginBinding>() {

    override val contentViewLayoutRes = R.layout.act_login
    override val mViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    override fun setupSubscription() {
        super.setupSubscription()
        mViewModel.onClearValidations.observe(this, Observer {
            clearValidations()
        })
        mViewModel.onShowValidations.observe(this, Observer{ validations ->
            showValidations(validations)
        })
        mViewModel.onLoginSucess.observe(this, Observer {
            Toast.makeText(this, "Datos válidos", Toast.LENGTH_LONG).show()
        })
        mViewModel.onSignUp.observe(this, Observer {
            showSignUp()
        })
    }

    private fun showSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun setup() {
        setupForgotPasswordUnderline()
    }

    private fun setupForgotPasswordUnderline() {
        val spannableText = SpannableString(getString(R.string.forgot_password))
        spannableText.setSpan(UnderlineSpan(), 0, spannableText.length, 0)
        tvForgotPassword.text = spannableText
    }

    private fun clearValidations() {
        inputEmail.error = null
        inputPassword.error = null
    }

    private fun showValidations(validations: List<LoginValidation>) {
        validations.forEach { validation ->
            when(validation) {
                EMPTY_EMAIL    -> inputEmail.error = getString(R.string.val_empty_email)
                INVALID_EMAIL  -> inputEmail.error = getString(R.string.val_invalid_email)
                EMTPY_PASSWORD -> inputPassword.error = getString(R.string.val_emtpy_password)
            }
        }
    }

}
