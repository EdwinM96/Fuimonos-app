package com.fuimonos.app.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    val toast = MutableLiveData<String>()

    fun start() {
        toast.value = "Prueba de LiveData y Koin"
    }

}
