package com.fuimonos.app.options

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fuimonos.app.R
import com.fuimonos.app.commons.AutoClearedValue
import com.fuimonos.app.commons.BaseViewModelFragment
import com.fuimonos.app.databinding.FrgOptionsBinding
import com.fuimonos.app.login.LoginActivity
import com.fuimonos.app.models.Option
import kotlinx.android.synthetic.main.frg_options.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OptionsFragment : BaseViewModelFragment<OptionsViewModel, FrgOptionsBinding>() {

    override val mViewModel: OptionsViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.frg_options
    private var optionsAdapter by AutoClearedValue<OptionsAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override fun setupVMSubscription() {
        super.setupVMSubscription()
        mViewModel.onShowOptions.observe(viewLifecycleOwner, Observer { options ->
            showOptions(options)
        })
        mViewModel.onShowSelectedOption.observe(viewLifecycleOwner, Observer { option ->
            showOptionScreen(option)
        })
        mViewModel.onLogoutSelected.observe(viewLifecycleOwner, Observer {
            showLogin()
        })
    }

    private fun setup() {
        setupOptionRecyclerView()
        mViewModel.start()
    }

    private fun setupOptionRecyclerView() {
        optionsAdapter = OptionsAdapter(mViewModel)
        rvOptions.layoutManager = LinearLayoutManager(context)
        rvOptions.adapter = optionsAdapter
    }

    private fun showOptions(options: List<Option>) {
        optionsAdapter.items = options
    }

    private fun showOptionScreen(option: Option) {
        Toast.makeText(context, "${getString(option.nameRes)} no está disponible", Toast.LENGTH_LONG).show()
    }

    private fun showLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity?.startActivity(intent)
        activity?.finish()
    }

}
