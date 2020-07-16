package com.fuimonos.app.di

import com.fuimonos.app.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SplashViewModel() }

}