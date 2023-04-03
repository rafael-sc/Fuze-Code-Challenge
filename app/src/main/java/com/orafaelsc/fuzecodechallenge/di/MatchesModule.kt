package com.orafaelsc.fuzecodechallenge.di

import com.orafaelsc.fuzecodechallenge.data.MatchesRepositoryImpl
import com.orafaelsc.fuzecodechallenge.data.network.MatchesApi
import com.orafaelsc.fuzecodechallenge.domain.repository.MatchesRepository
import com.orafaelsc.fuzecodechallenge.domain.usecase.MatchesUseCase
import com.orafaelsc.fuzecodechallenge.domain.usecase.MatchesUseCaseImpl
import com.orafaelsc.fuzecodechallenge.ui.matchDetails.MatchDetailsViewModel
import com.orafaelsc.fuzecodechallenge.ui.matches.MatchesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

// object to hold MainScreen dependencies
object MatchesModule {
    val module: Module = module {
        viewModel {
            MatchesViewModel(
                matchesUseCase = get(),
                coroutineDispatcherProvider = get()
            )
        }
        viewModel {
            MatchDetailsViewModel(
//                matchesUseCase = get(),
                coroutineDispatcherProvider = get()
            )
        }
        factory<MatchesUseCase> {
            MatchesUseCaseImpl(
                matchesRepository = get()
            )
        }
        factory<MatchesRepository> {
            MatchesRepositoryImpl(
                matchesAPI = get(),
                resourceProvider = get()
            )
        }
        factory<MatchesApi> {
            provideMatchesApi(
                get(qualifier = RetrofitQualifier)
            )
        }
    }

    private fun provideMatchesApi(retrofit: Retrofit) = retrofit.create(MatchesApi::class.java)
}
