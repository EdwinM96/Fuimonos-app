package com.fuimonos.app.userprofile

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.changepassword.ChangePasswordActivity
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.commons.picasso.CircleTransform
import com.fuimonos.app.databinding.ActUserProfileBinding
import com.fuimonos.app.login.LoginActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_user_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileActivity : BindableVMActivity<UserProfileViewModel, ActUserProfileBinding>() {

    override val mViewModel: UserProfileViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.act_user_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    override fun setupSubscription() {
        super.setupSubscription()
        mViewModel.onShowProfilePhoto.observe(this, Observer { profilePhoto ->
            showProfilePhoto(profilePhoto)
        })
        mViewModel.onChangePassword.observe(this, Observer {
            showChangePasswordScreen()
        })
        mViewModel.onLogout.observe(this, Observer {
            showLogin()
        })
    }

    private fun setup() {
        mViewModel.start()
    }

    private fun showProfilePhoto(profilePhoto: String) {
        Picasso.get()
            .load(profilePhoto)
            .transform(CircleTransform())
            .into(ivProfilePhoto)
    }

    private fun showChangePasswordScreen() {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
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
