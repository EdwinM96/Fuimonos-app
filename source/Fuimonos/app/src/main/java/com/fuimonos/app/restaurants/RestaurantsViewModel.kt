package com.fuimonos.app.restaurants

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.models.FoodCategory
import com.fuimonos.app.models.RestaurantsHeaded

class RestaurantsViewModel : BaseViewModel() {

    val onShowCategoriesRestaurants = MutableLiveData<Pair<List<FoodCategory>, List<RestaurantsHeaded>>>()

    fun start() {
        //TODO: CREAR MOCK PARA PRESENTAR CATEGORIAS Y RESTAURANTES
    }

}
