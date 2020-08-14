package com.fuimonos.app.commons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fuimonos.app.commons.exceptions.NoInternetException
import com.fuimonos.app.data.remote.ApiResult
import com.fuimonos.app.helpers.ConnectivityHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    val toast = MutableLiveData<String>()

    val onShowProgress = MutableLiveData<Boolean>()
    val onError = MutableLiveData<Exception>()

    fun launchCoroutines(context: CoroutineContext = Dispatchers.Main, checkInternet: Boolean = true, block: suspend () -> Unit) {
        viewModelScope.launch(context) {

            onShowProgress.value = true

            if(!checkInternet) {
                block.invoke()
                return@launch
            }

            if(!ConnectivityHelper.isInternetAvailable()) {
                onShowProgress.value = false
                onError.value = NoInternetException()
                return@launch
            }

            block.invoke()
        }
    }

    fun <T> ApiResult<T>.isSuccessDo(block: (T) -> Unit) {
        when(this) {
            is ApiResult.Success ->  {
                onShowProgress.value = false
                block.invoke(response)
            }
            is ApiResult.Error   -> {
                onShowProgress.value = false
                onError.value = exception
            }
        }
    }

}
