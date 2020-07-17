package com.fuimonos.app.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.commons.BaseViewModelActivity
import com.fuimonos.app.login.LoginActivity
import kotlinx.android.synthetic.main.act_splash.*
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

    override fun setupSubscription() {
        super.setupSubscription()
        mViewModel.onShowProgress.observe(this, Observer {
            showProgress(it)
        })
        mViewModel.onShowLoginScreen.observe(this, Observer {
            showLogin()
        })
    }

    private fun showLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    private fun showProgress(enable: Boolean) {
        val visibility = if(enable) {
            View.VISIBLE
        } else {
            View.GONE
        }

        progressBar.visibility = visibility
    }

}
