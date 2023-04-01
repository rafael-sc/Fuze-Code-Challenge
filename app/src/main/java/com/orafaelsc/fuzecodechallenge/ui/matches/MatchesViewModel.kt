package com.orafaelsc.fuzecodechallenge.ui.matches

import androidx.lifecycle.viewModelScope
import com.orafaelsc.fuzecodechallenge.commom.BaseViewModel
import com.orafaelsc.fuzecodechallenge.commom.CoroutineDispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MatchesViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) :
    BaseViewModel(coroutineDispatcherProvider) {

    private val navigateToMatchesFragment = MutableSharedFlow<Boolean>()
    fun navigateToDetailsFragment(): SharedFlow<Boolean> = navigateToMatchesFragment

    fun init() {
        viewModelScope.launch(mainExceptionHandler) {
            delay(2500)
            navigateToMatchesFragment.emit(true)
        }
    }
}
