package com.fuimonos.app.commons

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.ext.disableTouch
import com.fuimonos.app.ext.enableTouch
import com.fuimonos.app.ext.hideKeyboard
import com.fuimonos.app.helpers.DialogHelper
import timber.log.Timber

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
        mViewModel.onShowProgress.observe(this, Observer {
            showProgress(it)
        })
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

    protected fun showProgress(visible: Boolean) {

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar?.let {
            if(visible) {
                it.visibility = View.VISIBLE
                this.disableTouch()
                this.hideKeyboard()
                return
            }
            it.visibility = View.GONE
            this.enableTouch()
        } ?: run {
            Timber.e("progressBar is not defined in layout")
            return
        }

    }

}
