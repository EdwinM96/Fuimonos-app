package com.fuimonos.app.di

import com.fuimonos.app.appnavigator.AppNavigatorViewModel
import com.fuimonos.app.data.ILoginRepository
import com.fuimonos.app.data.LoginRepository
import com.fuimonos.app.data.local.SessionDataPref
import com.fuimonos.app.data.local.sharedprefs.CryptedPrefManager
import com.fuimonos.app.data.local.sharedprefs.IPrefManager
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
import com.fuimonos.app.userprofile.UserProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SECURE_PREF_KEY = "securePref"

val appModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignUpViewModel() }
    viewModel { RecoverPasswordViewModel() }
    viewModel { AppNavigatorViewModel() }
    viewModel { RestaurantsViewModel(get()) }
    viewModel { (restaurant: Restaurant) -> RestaurantMenuViewModel(restaurant, get()) }
    viewModel { (food: Food) -> FoodComplementsViewModel(food) }
    viewModel { ShoppingCartViewModel() }
    viewModel { OptionsViewModel(get()) }
    viewModel { UserProfileViewModel(get()) }
}

val repositoriesModule = module {
    single<IApiHandler> { ApiHandler() }
    single<ILoginRepository> { LoginRepository(get(), get()) }
}

val encryptionModule = module {
    single<IPrefManager>(named(SECURE_PREF_KEY)) { CryptedPrefManager(get()) }
    single { SessionDataPref(get(named(SECURE_PREF_KEY))) }
}
