package com.orafaelsc.fuzecodechallenge.ui.matches

import androidx.lifecycle.viewModelScope
import com.orafaelsc.fuzecodechallenge.commom.BaseViewModel
import com.orafaelsc.fuzecodechallenge.commom.CoroutineDispatcherProvider
import com.orafaelsc.fuzecodechallenge.domain.usecase.MatchesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MatchesViewModel(
    private val matchesUseCase: MatchesUseCase,
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
) :
    BaseViewModel(coroutineDispatcherProvider) {

    private val navigateToDetailsFragment = MutableSharedFlow<Boolean>()
    fun navigateToDetailsFragment(): SharedFlow<Boolean> = navigateToDetailsFragment

    private val matches = MutableSharedFlow<Any>()
    fun matches(): SharedFlow<Any> = matches

    fun init() {
        viewModelScope.launch(mainExceptionHandler) {
            val m = matchesUseCase.getMatches()
            matches.emit(m)
        }
    }
}
