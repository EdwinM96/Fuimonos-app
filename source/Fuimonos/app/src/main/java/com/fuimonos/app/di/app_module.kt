package com.fuimonos.app.di

import com.fuimonos.app.appnavigator.AppNavigatorViewModel
import com.fuimonos.app.data.ILoginRepository
import com.fuimonos.app.data.LoginRepository
import com.fuimonos.app.data.remote.ApiHandler
import com.fuimonos.app.data.remote.IApiHandler
import com.fuimonos.app.foodcomplements.FoodComplementsViewModel
import com.fuimonos.app.login.LoginViewModel
import com.fuimonos.app.models.Food
import com.fuimonos.app.models.Restaurant
import com.fuimonos.app.options.OptionsViewModel
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
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel() }
    viewModel { RecoverPasswordViewModel() }
    viewModel { AppNavigatorViewModel() }
    viewModel { RestaurantsViewModel() }
    viewModel { (restaurant: Restaurant) -> RestaurantMenuViewModel(restaurant) }
    viewModel { (food: Food) -> FoodComplementsViewModel(food) }
    viewModel { ShoppingCartViewModel() }
    viewModel { OptionsViewModel() }
}

val repositoriesModule = module {
    single { ApiHandler() as IApiHandler }
    single { LoginRepository(get(), get()) as ILoginRepository }
}