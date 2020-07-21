package com.fuimonos.app.restaurants

import com.fuimonos.app.R
import com.fuimonos.app.commons.BaseViewModelFragment
import com.fuimonos.app.databinding.FrgRestaurantsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantsFragment : BaseViewModelFragment<RestaurantsViewModel, FrgRestaurantsBinding>() {

    override val mViewModel: RestaurantsViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.frg_restaurants

    companion object {
        fun newInstance() : RestaurantsFragment {
            return RestaurantsFragment()
        }
    }

}

