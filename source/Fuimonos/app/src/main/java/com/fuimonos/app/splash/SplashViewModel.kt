package com.fuimonos.app.splash

import com.fuimonos.app.commons.BaseViewModel

class SplashViewModel : BaseViewModel() {

    fun start() {
        toast.value = "Prueba de LiveData y Koin"
    }

}
