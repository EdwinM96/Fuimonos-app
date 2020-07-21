package com.fuimonos.app.commons

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.fuimonos.app.helpers.DialogHelper

abstract class BaseViewModelActivity<VM: BaseViewModel> : AppCompatActivity() {

    abstract val mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBase()
    }

    private fun setupBase() {
        setupSubscription()
    }

    protected open fun setupSubscription() {
        mViewModel.toast.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        mViewModel.onError.observe(this, Observer { error ->
            showError(error)
        })
    }

    private fun showError(error: Exception) {
        val dialogErrorData = ErrorDialogDataBuilder.buildFrom(error)
        DialogHelper.showSingleOptionDialog(this, dialogErrorData)
    }

}
