package com.orafaelsc.fuzecodechallenge.di

import com.orafaelsc.fuzecodechallenge.commom.CoroutineDispatcherProvider
import com.orafaelsc.fuzecodechallenge.commom.ResourceProvider
import com.orafaelsc.fuzecodechallenge.commom.ResourceProviderImpl
import org.koin.dsl.module

// object to hold MainScreen dependencies
object ProvidersModule {
    val instance = module {
        single<ResourceProvider> { ResourceProviderImpl(get()) }

        single<CoroutineDispatcherProvider> {
            CoroutineDispatcherProvider()
        }
    }
}
