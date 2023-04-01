package com.orafaelsc.fuzecodechallenge.di

import com.orafaelsc.fuzecodechallenge.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

// object to hold MainScreen dependencies
object MainModule {
    val module: Module = module {
        viewModel {
            MainViewModel(
                coroutineDispatcherProvider = get(),
            )
        }
    }
}
