package com.fuimonos.app.restaurantmenu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fuimonos.app.R
import com.fuimonos.app.commons.BaseViewModelFragment
import com.fuimonos.app.databinding.FrgRestaurantMenuBinding
import com.fuimonos.app.ext.getArg
import com.fuimonos.app.models.Restaurant
import kotlinx.android.synthetic.main.frg_restaurant_menu.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantMenuFragment : BaseViewModelFragment<RestaurantMenuViewModel, FrgRestaurantMenuBinding>() {

    private val restaurant by getArg<Restaurant>(RESTAURANT_ARG)

    override val mViewModel: RestaurantMenuViewModel by viewModel { parametersOf(restaurant) }
    override val contentViewLayoutRes = R.layout.frg_restaurant_menu

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupBack()
    }

    private fun setupBack() {
        ivBack.setOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        }
    }

    companion object {
        const val RESTAURANT_ARG = "RESTAURANT"
    }

}
