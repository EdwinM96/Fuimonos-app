package com.fuimonos.app.di

import com.fuimonos.app.appnavigator.AppNavigatorViewModel
import com.fuimonos.app.foodcomplements.FoodComplementsViewModel
import com.fuimonos.app.login.LoginViewModel
import com.fuimonos.app.models.Food
import com.fuimonos.app.models.Restaurant
import com.fuimonos.app.recoverpassword.RecoverPasswordViewModel
import com.fuimonos.app.restaurantmenu.RestaurantMenuViewModel
import com.fuimonos.app.restaurants.RestaurantsViewModel
import com.fuimonos.app.shoppingcart.ShoppingCartViewModel
import com.fuimonos.app.signup.SignUpViewModel
import com.fuimonos.app.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { SignUpViewModel() }
    viewModel { RecoverPasswordViewModel() }
    viewModel { AppNavigatorViewModel() }
    viewModel { RestaurantsViewModel() }
    viewModel { (restaurant: Restaurant) -> RestaurantMenuViewModel(restaurant) }
    viewModel { (food: Food) -> FoodComplementsViewModel(food) }
    viewModel { ShoppingCartViewModel() }
}
