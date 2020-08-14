package com.fuimonos.app.recoverpassword

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fuimonos.app.R
import com.fuimonos.app.login.LoginActivity
import kotlinx.android.synthetic.main.act_recover_pass_success.*

class RecPasswordSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_recover_pass_success)
        setup()
    }

    private fun setup() {
        setupBtnLogin()
    }

    private fun setupBtnLogin() {
        btnLogin.setOnClickListener {
            showLogin()
        }
    }

    private fun showLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

}
