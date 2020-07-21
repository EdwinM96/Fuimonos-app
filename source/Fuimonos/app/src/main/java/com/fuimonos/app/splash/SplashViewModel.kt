package com.fuimonos.app.splash

import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import kotlinx.coroutines.delay

class SplashViewModel : BaseViewModel() {

    val onShowLoginScreen = SingleLiveEvent<Nothing>()
    val onShowMainScreen = SingleLiveEvent<Nothing>()

    fun start() {
        launchCoroutines(checkInternet = false) {
            //TODO: AQUI SE DEBE VALIDAR SI HAY UNA SESIÓN ACTIVA ENVIAR A LOGIN O PRINCIPAL
            delay(3000)
            onShowProgress.value = false
            onShowLoginScreen.call()
        }
    }

}
