package com.orafaelsc.fuzecodechallenge.ui.matchDetails

import androidx.lifecycle.viewModelScope
import com.orafaelsc.fuzecodechallenge.commom.BaseViewModel
import com.orafaelsc.fuzecodechallenge.commom.CoroutineDispatcherProvider
import com.orafaelsc.fuzecodechallenge.domain.usecase.MatchesUseCase
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MatchDetailsViewModel(
    private val matchesUseCase: MatchesUseCase,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(coroutineDispatcherProvider) {

    private val loadedMatches = mutableListOf<Match>()
    private val loadingState = MutableSharedFlow<Boolean>()
    fun loadingState(): SharedFlow<Boolean> = loadingState
    private val matches = MutableSharedFlow<List<Match>>()
    fun matches(): SharedFlow<List<Match>> = matches

    fun init() {
        viewModelScope.launch(mainExceptionHandler) {
            loadingState.emit(true)
            val result = matchesUseCase.getMatches()
            loadedMatches.clear()
            loadedMatches.addAll(result)
            matches.emit(result)
            loadingState.emit(false)
        }
    }
}
