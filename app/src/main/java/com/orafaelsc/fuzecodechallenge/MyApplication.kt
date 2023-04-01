package com.orafaelsc.fuzecodechallenge

import android.app.Application
import com.orafaelsc.fuzecodechallenge.di.MainModule
import com.orafaelsc.fuzecodechallenge.di.ProvidersModule
import com.orafaelsc.fuzecodechallenge.di.RetrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(
                RetrofitModule.instance,
                MainModule.module,
                ProvidersModule.instance,
            )
            androidContext(this@MyApplication)
        }
    }
}
