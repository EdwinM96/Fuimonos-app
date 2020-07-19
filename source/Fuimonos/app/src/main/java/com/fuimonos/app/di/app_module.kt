package com.fuimonos.app.di

import com.fuimonos.app.login.LoginViewModel
import com.fuimonos.app.recoverpassword.RecoverPasswordViewModel
import com.fuimonos.app.signup.SignUpViewModel
import com.fuimonos.app.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { SignUpViewModel() }
    viewModel { RecoverPasswordViewModel() }
}