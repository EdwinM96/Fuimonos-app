package com.fuimonos.app.login

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.databinding.ActLoginBinding
import kotlinx.android.synthetic.main.act_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BindableVMActivity<LoginViewModel, ActLoginBinding>() {

    override val contentViewLayoutRes = R.layout.act_login
    override val mViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupForgotPasswordUnderline()
    }

    private fun setupForgotPasswordUnderline() {
        val spannableText = SpannableString(getString(R.string.forgot_password))
        spannableText.setSpan(UnderlineSpan(), 0, spannableText.length, 0)
        tvForgotPassword.text = spannableText
    }

}
