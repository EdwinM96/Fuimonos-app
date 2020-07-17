package com.fuimonos.app.commons

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.fuimonos.app.BR

abstract class BindableVMActivity<VM: BaseViewModel, BINDING: ViewDataBinding> : BaseViewModelActivity<VM>() {

    abstract val contentViewLayoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        val binding: BINDING = DataBindingUtil.setContentView(this, contentViewLayoutRes)
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
    }

}
