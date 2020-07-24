package com.fuimonos.app.restaurantmenu

import android.util.SparseArray
import androidx.core.util.set
import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.models.Food
import com.fuimonos.app.models.Menu
import com.fuimonos.app.models.Restaurant

class RestaurantMenuViewModel(val restaurant: Restaurant) : BaseViewModel() {

    val onShowRestaurantLogo = MutableLiveData<String>()
    val onShowMenuTabs = MutableLiveData<List<Menu>>()
    val onShowFoods = MutableLiveData<List<Food>>()

    private lateinit var menus: List<Menu>
    private val foodsByMenuId = SparseArray<List<Food>>()

    fun start() {
        onShowRestaurantLogo.value = restaurant.logo
        menus = getMenus()
        onShowMenuTabs.value = menus
        loadFoodByMenuId()
        onShowFoods.value = foodsByMenuId[menus.first().id]
    }

    private fun getMenus(): List<Menu> {
        return listOf(
            Menu(1, "Hamburguesas"),
            Menu(2, "Ensaladas"),
            Menu(3, "Desayunos"),
            Menu(4, "Postres"),
            Menu(5, "Bebidas")
        )
    }

    private fun loadFoodByMenuId() {
        menus.forEach { menu ->
            foodsByMenuId[menu.id] = listOf(
                Food(1,
                     menu.name,
                     "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam ",
                     6.99f,
                     "https://drive.google.com/uc?id=1BB3WINvwHfYWzqUgHd9ye0l4RR8PWRV7"),
                Food(2,
                    menu.name,
                     "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam ",
                     6.99f,
                     "https://drive.google.com/uc?id=1BB3WINvwHfYWzqUgHd9ye0l4RR8PWRV7"),
                Food(3,
                    menu.name,
                     "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam ",
                     6.99f,
                     "https://drive.google.com/uc?id=1BB3WINvwHfYWzqUgHd9ye0l4RR8PWRV7"),
                Food(4,
                    menu.name,
                     "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam ",
                     6.99f,
                     "https://drive.google.com/uc?id=1BB3WINvwHfYWzqUgHd9ye0l4RR8PWRV7"),
                Food(5,
                    menu.name,
                     "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam ",
                     6.99f,
                     "https://drive.google.com/uc?id=1BB3WINvwHfYWzqUgHd9ye0l4RR8PWRV7"),
                Food(6,
                    menu.name,
                     "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam ",
                     6.99f,
                     "https://drive.google.com/uc?id=1BB3WINvwHfYWzqUgHd9ye0l4RR8PWRV7")
            )
        }
    }

    fun onSelectFood(food: Food) {
        toast.value = food.name
    }

    fun requestFoodFrom(menuId: Int) {
        onShowFoods.value = foodsByMenuId[menuId]
    }

}
