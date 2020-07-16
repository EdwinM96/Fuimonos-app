package com.fuimonos.app.commons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val toast = MutableLiveData<String>()

}