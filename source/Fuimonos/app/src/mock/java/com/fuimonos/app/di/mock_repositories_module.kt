package com.fuimonos.app.di

import com.fuimonos.app.data.mock.MockLoginApi
import com.fuimonos.app.data.remote.ILoginApi
import org.koin.dsl.module

val dataSourceModule = module {
    single { MockLoginApi() as ILoginApi}
}
