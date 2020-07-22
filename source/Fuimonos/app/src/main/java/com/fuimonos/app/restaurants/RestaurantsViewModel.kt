package com.fuimonos.app.restaurants

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.models.FoodCategory
import com.fuimonos.app.models.Restaurant
import com.fuimonos.app.models.RestaurantsHeaded

class RestaurantsViewModel : BaseViewModel() {

    val onShowCategoriesRestaurants = MutableLiveData<Pair<List<FoodCategory>, List<RestaurantsHeaded>>>()

    fun start() {
        val categories = listOf(
            FoodCategory(1, "https://drive.google.com/uc?id=1TAl96cfsg-UgNrcJlI0KSiVdoPmRETI8", "Hamburguesa", 12),
            FoodCategory(2, "https://drive.google.com/uc?id=1octxHVNnNlOqPE9rPvSWnAvfcZwhZO2A", "Pizza", 9),
            FoodCategory(3, "https://drive.google.com/uc?id=101Lkq4maL4oBl2YzVOTC7ouSn0oJQbyE", "Pollo", 10)
        )

        val restaurantsHeaded = listOf(
            RestaurantsHeaded("Restaurantes populares", listOf(
                Restaurant(1,
                    "https://drive.google.com/uc?id=1OLO3cyVeNjSQ6c23EDRgT5c6QPFcy7uo",
                    "https://drive.google.com/uc?id=1720XxkxDBDrq3jVVr5YhQ4clQnGDotQ9",
                "Que brava!", "$8.50"),
                Restaurant(2,
                    "https://drive.google.com/uc?id=1OLO3cyVeNjSQ6c23EDRgT5c6QPFcy7uo",
                    "https://drive.google.com/uc?id=1720XxkxDBDrq3jVVr5YhQ4clQnGDotQ9",
                    "Que brava!", "$8.50"),
                Restaurant(3,
                    "https://drive.google.com/uc?id=1OLO3cyVeNjSQ6c23EDRgT5c6QPFcy7uo",
                    "https://drive.google.com/uc?id=1720XxkxDBDrq3jVVr5YhQ4clQnGDotQ9",
                    "Que brava!", "$8.50")
            )),
            RestaurantsHeaded("Restaurantes nuevos", listOf(
                Restaurant(1,
                    "https://drive.google.com/uc?id=1OLO3cyVeNjSQ6c23EDRgT5c6QPFcy7uo",
                    "https://drive.google.com/uc?id=1720XxkxDBDrq3jVVr5YhQ4clQnGDotQ9",
                    "Que brava!", "$8.50"),
                Restaurant(2,
                    "https://drive.google.com/uc?id=1OLO3cyVeNjSQ6c23EDRgT5c6QPFcy7uo",
                    "https://drive.google.com/uc?id=1720XxkxDBDrq3jVVr5YhQ4clQnGDotQ9",
                    "Que brava!", "$8.50"),
                Restaurant(3,
                    "https://drive.google.com/uc?id=1OLO3cyVeNjSQ6c23EDRgT5c6QPFcy7uo",
                    "https://drive.google.com/uc?id=1720XxkxDBDrq3jVVr5YhQ4clQnGDotQ9",
                    "Que brava!", "$8.50")
            ))
        )

        onShowCategoriesRestaurants.value = Pair(categories, restaurantsHeaded)
    }

    fun onSelectCategory(category: FoodCategory) {
        toast.value = category.categoryName
    }

    fun onSelectRestaurant(restaurant: Restaurant) {
        toast.value = restaurant.name
    }

}
