package com.fuimonos.app.splash

import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import com.fuimonos.app.data.local.SessionDataPref
import kotlinx.coroutines.delay

class SplashViewModel(private val sessionDataPref: SessionDataPref) : BaseViewModel() {

    val onShowLoginScreen = SingleLiveEvent<Nothing>()
    val onShowMainScreen = SingleLiveEvent<Nothing>()

    fun start() {
        launchCoroutines(checkInternet = false) {
            delay(2000)
            onShowProgress.value = false

            if(!isSessionActive()) {
                onShowLoginScreen.call()
                return@launchCoroutines
            }

            onShowMainScreen.call()
        }
    }

    private fun isSessionActive(): Boolean {
        return sessionDataPref.existsSessionData()
    }

}
