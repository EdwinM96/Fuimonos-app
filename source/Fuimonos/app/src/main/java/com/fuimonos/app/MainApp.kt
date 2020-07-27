package com.fuimonos.app

import android.app.Application
import com.fuimonos.app.di.appModule
import com.fuimonos.app.di.dataSourceModule
import com.fuimonos.app.di.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@MainApp)
            modules(appModule, dataSourceModule, repositoriesModule)
        }
    }

}
