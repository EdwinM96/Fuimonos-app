package com.fuimonos.app.appnavigator

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.databinding.ActAppNavigatorBinding
import com.fuimonos.app.restaurants.RestaurantsFragment
import kotlinx.android.synthetic.main.act_app_navigator.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppNavigatorActivity : BindableVMActivity<AppNavigatorViewModel, ActAppNavigatorBinding>() {

    override val mViewModel: AppNavigatorViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.act_app_navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        bottomNavigation.selectedItemId =R.id.menuHome
        show(RestaurantsFragment.newInstance())

        bottomNavigation.setOnNavigationItemSelectedListener { item ->

            var selectedFragment: Fragment? = null
            var flag: Boolean = false

            when(item.itemId) {
                R.id.menuHome -> {
                    selectedFragment = RestaurantsFragment.newInstance()
                    flag = true
                }
                R.id.menuShoppingCart -> {
                    printUnavailableOption()
                    flag = false
                }
                R.id.menuOptions -> {
                    printUnavailableOption()
                    flag = false
                }
            }

            selectedFragment?.let {
                show(selectedFragment)
            }

            flag

        }

    }

    private fun show(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_close_exit)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    protected fun printUnavailableOption() {
        Toast.makeText(this, "Opción no disponible", Toast.LENGTH_SHORT).show()
    }

}
