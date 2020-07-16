package com.fuimonos.app.splash

import android.os.Bundle
import com.fuimonos.app.R
import com.fuimonos.app.commons.BaseViewModelActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseViewModelActivity<SplashViewModel>() {

    override val mViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash)
        setup()
    }

    private fun setup() {
        mViewModel.start()
    }

}
