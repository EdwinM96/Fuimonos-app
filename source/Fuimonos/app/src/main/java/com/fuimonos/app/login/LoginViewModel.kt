package com.fuimonos.app.login

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel

class LoginViewModel : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun onForgotPassword() {
        toast.value = "En construcción"
    }

}
