package com.fuimonos.app.commons

import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.ViewDataBinding
import com.fuimonos.app.R
import timber.log.Timber

abstract class BackViewModelActivity<VM: BaseViewModel, BINDING : ViewDataBinding>: BindableVMActivity<VM, BINDING>() {

    private var ivBack: AppCompatImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBack()
    }

    protected fun setupBack() {
        ivBack = findViewById(R.id.ivBack)
        ivBack?.setOnClickListener {
            onBackPressed()
        } ?: Timber.e("ivBack is not defined in layout")
    }

}
