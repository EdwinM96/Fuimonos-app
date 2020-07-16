package com.fuimonos.app.commons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    val toast = MutableLiveData<String>()

    val onShowProgress = MutableLiveData<Boolean>()

    fun launchCoroutines(context: CoroutineContext = Dispatchers.Main, block: suspend () -> Unit) {
        viewModelScope.launch(context) {
            onShowProgress.value = true
            block.invoke()
        }
    }


}
