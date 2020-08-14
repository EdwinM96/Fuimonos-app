package com.fuimonos.app.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.ViewDataBinding
import com.fuimonos.app.R
import timber.log.Timber

abstract class BackViewModelFragment<VM: BaseViewModel, BINDING : ViewDataBinding> : BaseViewModelFragment<VM, BINDING>() {

    private var ivBack: AppCompatImageView? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBack()
    }

    protected fun setupBack() {
        ivBack = view?.findViewById(R.id.ivBack)
        ivBack?.setOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        } ?: Timber.e("ivBack is not defined in layout")
    }

}
