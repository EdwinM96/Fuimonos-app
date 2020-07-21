package com.fuimonos.app.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.fuimonos.app.BR
import com.fuimonos.app.R
import com.fuimonos.app.ext.disableTouch
import com.fuimonos.app.ext.enableTouch
import com.fuimonos.app.ext.hideKeyboard
import timber.log.Timber

abstract class BaseViewModelFragment<VM: BaseViewModel, BINDING : ViewDataBinding> : Fragment() {

	abstract val mViewModel: VM
	abstract val contentViewLayoutRes: Int

	private var progressBar: ProgressBar? = null
	
	override fun onCreateView(inflater: LayoutInflater,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		val binding = setupDataBinding(inflater, container)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupBase(view)
	}
	
	private fun setupDataBinding(inflater: LayoutInflater,
	                             container: ViewGroup?) : ViewDataBinding {
		val binding: BINDING = DataBindingUtil.inflate(inflater,
			contentViewLayoutRes,
			container,
			false)
		binding.setVariable(BR.viewModel, mViewModel)
		binding.lifecycleOwner = this
		return binding
	}
	
	private fun setupBase(view: View) {
		progressBar = view.findViewById(R.id.progressBar)
		setupVMSubscription()
	}
	
	protected open fun setupVMSubscription() {
		mViewModel.toast.observe(viewLifecycleOwner, Observer {
			Toast.makeText(context, it, Toast.LENGTH_LONG).show()
		})
		mViewModel.onShowProgress.observe(viewLifecycleOwner, Observer {
			showProgress(it)
		})
	}
	
	protected open fun showProgress(visible: Boolean) {
		
		progressBar?.let {
			if(visible) {
				it.visibility = View.VISIBLE
				activity?.disableTouch()
				activity?.hideKeyboard()
				return
			}
			it.visibility = View.GONE
			activity?.enableTouch()
		} ?: run {
			Timber.e("progressBar is not defined in layout")
			return
		}
		
	}
	
}
