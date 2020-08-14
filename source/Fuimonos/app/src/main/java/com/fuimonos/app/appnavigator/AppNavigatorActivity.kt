package com.fuimonos.app.appnavigator

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.databinding.ActAppNavigatorBinding
import com.fuimonos.app.restaurants.RestaurantsFragment
import kotlinx.android.synthetic.main.act_app_navigator.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppNavigatorActivity : BindableVMActivity<AppNavigatorViewModel, ActAppNavigatorBinding>() {

    override val mViewModel: AppNavigatorViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.act_app_navigator
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationView()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationView()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigationView() {
        val navGraphIds = listOf(R.navigation.menu_home,
                                 R.navigation.menu_shopping_cart,
                                 R.navigation.menu_options)
        val navController = bottomNavigation.setupWithNavController(navGraphIds,
                                                                    supportFragmentManager,
                                                                    R.id.fragmentContainer,
                                                                    intent)
        currentNavController = navController
    }

}
