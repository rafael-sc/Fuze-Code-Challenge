package com.orafaelsc.fuzecodechallenge.di

import com.orafaelsc.fuzecodechallenge.ui.matches.MatchesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

// object to hold MainScreen dependencies
object MatchesModule {
    val module: Module = module {
        viewModel {
            MatchesViewModel(
                coroutineDispatcherProvider = get(),
            )
        }
    }
}
