package com.fuimonos.app.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.commons.BaseViewModelFragment
import com.fuimonos.app.databinding.FrgRestaurantsBinding
import com.fuimonos.app.models.FoodCategory
import com.fuimonos.app.models.RestaurantsHeaded
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantsFragment : BaseViewModelFragment<RestaurantsViewModel, FrgRestaurantsBinding>() {

    override val mViewModel: RestaurantsViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.frg_restaurants

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override fun setupVMSubscription() {
        super.setupVMSubscription()
        mViewModel.onShowCategoriesRestaurants.observe(this, Observer { pair ->
            showCategoriesRestaurants(pair.first, pair.second)
        })
    }

    private fun setup() {
        mViewModel.start()
    }

    private fun showCategoriesRestaurants(categories: List<FoodCategory>,
                                          restaurants: List<RestaurantsHeaded>) {

        //TODO: CONFIGURAR RECYCLERVIEW

    }

    companion object {
        fun newInstance() : RestaurantsFragment {
            return RestaurantsFragment()
        }
    }

}
