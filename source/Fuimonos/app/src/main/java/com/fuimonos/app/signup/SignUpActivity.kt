package com.fuimonos.app.signup

import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.databinding.ActSignupBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BindableVMActivity<SignUpViewModel, ActSignupBinding>() {

    override val mViewModel: SignUpViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.act_signup

}
