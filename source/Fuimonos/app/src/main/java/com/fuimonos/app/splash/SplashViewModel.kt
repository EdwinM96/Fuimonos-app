package com.fuimonos.app.splash

import com.fuimonos.app.commons.BaseViewModel
import kotlinx.coroutines.delay

class SplashViewModel : BaseViewModel() {

    fun start() {
        launchCoroutines {
            delay(3000)
            onShowProgress.value = false
            toast.value = "Splash exitoso"
        }
    }

}
