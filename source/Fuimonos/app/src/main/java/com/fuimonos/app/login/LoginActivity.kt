package com.fuimonos.app.login

import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.databinding.ActLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BindableVMActivity<LoginViewModel, ActLoginBinding>() {

    override val contentViewLayoutRes = R.layout.act_login
    override val mViewModel: LoginViewModel by viewModel()

}
