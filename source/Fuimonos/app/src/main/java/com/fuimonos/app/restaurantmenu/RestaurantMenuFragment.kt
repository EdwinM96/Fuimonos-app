package com.fuimonos.app.restaurantmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fuimonos.app.R
import com.fuimonos.app.commons.AutoClearedValue
import com.fuimonos.app.commons.BackViewModelFragment
import com.fuimonos.app.commons.BaseViewModelFragment
import com.fuimonos.app.commons.picasso.CircleTransform
import com.fuimonos.app.databinding.FrgRestaurantMenuBinding
import com.fuimonos.app.ext.getArg
import com.fuimonos.app.foodcomplements.FoodComplementsFragment.Companion.FOOD_ARG
import com.fuimonos.app.models.Food
import com.fuimonos.app.models.Menu
import com.fuimonos.app.models.Restaurant
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.frg_restaurant_menu.*
import kotlinx.android.synthetic.main.inc_profile_photo.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantMenuFragment : BackViewModelFragment<RestaurantMenuViewModel, FrgRestaurantMenuBinding>() {

    private val restaurant by getArg<Restaurant>(RESTAURANT_ARG)

    override val mViewModel: RestaurantMenuViewModel by viewModel { parametersOf(restaurant) }
    override val contentViewLayoutRes = R.layout.frg_restaurant_menu
    private var foodsAdapter by AutoClearedValue<FoodsAdapter>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setup()
    }

    override fun setupVMSubscription() {
        super.setupVMSubscription()
        mViewModel.onShowProfilePhoto.observe(viewLifecycleOwner, Observer { profilePhoto ->
            showProfilePhoto(profilePhoto)
        })
        mViewModel.onShowRestaurantLogo.observe(viewLifecycleOwner, Observer { logo ->
            setupRestaurantLogo(logo)
        })
        mViewModel.onShowMenuTabs.observe(viewLifecycleOwner, Observer { menus ->
            showMenuTabs(menus)
        })
        mViewModel.onShowFoods.observe(viewLifecycleOwner, Observer { foods ->
            showFoods(foods)
        })
        mViewModel.onShowFoodComplements.observe(viewLifecycleOwner, Observer { food ->
            showFoodComplements(food)
        })
    }

    private fun setup() {
        mViewModel.start()
        setupMenuTabs()
        setupFoodRecyclerView()
    }

    private fun showProfilePhoto(profilePhoto: String) {
        Picasso.get()
            .load(profilePhoto)
            .transform(CircleTransform())
            .into(ivProfilePhoto)
    }

    private fun setupRestaurantLogo(logo: String?) {
        Picasso.get()
            .load(logo)
            .transform(CircleTransform())
            .into(ivRestaurantLogo)
    }

    private fun setupMenuTabs() {
        menuTab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                mViewModel.requestFoodFrom(tab.tag as Int)
            }

        })
    }

    private fun setupFoodRecyclerView() {
        foodsAdapter = FoodsAdapter(mViewModel)
        rvFoods.layoutManager = LinearLayoutManager(context)
        rvFoods.adapter = foodsAdapter
    }

    private fun showMenuTabs(menus: List<Menu>) {
        menus.forEach {
            addMenuTab(it)
        }
    }

    private fun addMenuTab(menu: Menu) {
        val tab = menuTab.newTab()
        tab.tag = menu.id
        tab.text = menu.name
        menuTab.addTab(tab)
    }

    private fun showFoods(foods: List<Food>) {
        foodsAdapter.items = foods
    }

    private fun showFoodComplements(food: Food) {
        val bundleArgs = bundleOf(FOOD_ARG to food)
        findNavController().navigate(R.id.action_menu_to_food_complement,
                                     bundleArgs)
    }

    companion object {
        const val RESTAURANT_ARG = "RESTAURANT"
    }

}
