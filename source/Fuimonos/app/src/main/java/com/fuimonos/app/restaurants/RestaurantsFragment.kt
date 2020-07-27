package com.fuimonos.app.restaurants

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fuimonos.app.R
import com.fuimonos.app.commons.AutoClearedValue
import com.fuimonos.app.commons.BaseViewModelFragment
import com.fuimonos.app.commons.picasso.CircleTransform
import com.fuimonos.app.databinding.FrgRestaurantsBinding
import com.fuimonos.app.models.FoodCategory
import com.fuimonos.app.models.Restaurant
import com.fuimonos.app.models.RestaurantsHeaded
import com.fuimonos.app.restaurantmenu.RestaurantMenuFragment.Companion.RESTAURANT_ARG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.frg_restaurants.*
import kotlinx.android.synthetic.main.inc_profile_photo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantsFragment : BaseViewModelFragment<RestaurantsViewModel, FrgRestaurantsBinding>() {

    override val mViewModel: RestaurantsViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.frg_restaurants
    private var categoriesRestaurantsAdapter by AutoClearedValue<CategoriesRestaurantsAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override fun setupVMSubscription() {
        super.setupVMSubscription()
        mViewModel.onShowProfilePhoto.observe(viewLifecycleOwner, Observer { profilePhoto ->
            showProfilePhoto(profilePhoto)
        })
        mViewModel.onShowCategoriesRestaurants.observe(viewLifecycleOwner, Observer { pair ->
            showCategoriesRestaurants(pair.first, pair.second)
        })
        mViewModel.onRestaurantSelected.observe(viewLifecycleOwner, Observer { restaurant ->
            showRestaurantMenu(restaurant)
        })
    }

    private fun setup() {
        mViewModel.start()
        setupRecycleViewRestaurants()
    }

    private fun setupRecycleViewRestaurants() {
        categoriesRestaurantsAdapter = CategoriesRestaurantsAdapter(mViewModel)
        rvRestaurants.layoutManager = LinearLayoutManager(context)
        rvRestaurants.adapter = categoriesRestaurantsAdapter
    }

    private fun showProfilePhoto(profilePhoto: String) {
        Picasso.get()
            .load(profilePhoto)
            .transform(CircleTransform())
            .into(ivProfilePhoto)
    }

    private fun showCategoriesRestaurants(categories: List<FoodCategory>,
                                          restaurants: List<RestaurantsHeaded>) {
        categoriesRestaurantsAdapter.itemCategories = categories
        categoriesRestaurantsAdapter.itemRestaurantsHeaded = restaurants
    }

    private fun showRestaurantMenu(restaurant: Restaurant) {
        val bundleArgs = bundleOf(RESTAURANT_ARG to restaurant)
        findNavController().navigate(R.id.action_restaurants_to_restaurant_menu,
                                     bundleArgs)
    }

}
