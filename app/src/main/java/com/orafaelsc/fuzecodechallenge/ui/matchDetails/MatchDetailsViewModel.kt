package com.orafaelsc.fuzecodechallenge.ui.matchDetails

import androidx.lifecycle.viewModelScope
import com.orafaelsc.fuzecodechallenge.commom.BaseViewModel
import com.orafaelsc.fuzecodechallenge.commom.CoroutineDispatcherProvider
import com.orafaelsc.fuzecodechallenge.domain.usecase.TeamUseCase
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Player
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MatchDetailsViewModel(
    private val teamUseCase: TeamUseCase,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(coroutineDispatcherProvider) {

    private val loadingState = MutableSharedFlow<Boolean>()
    fun loadingState(): SharedFlow<Boolean> = loadingState

    private val matchDetails = MutableSharedFlow<Match>()
    fun matchDetails(): SharedFlow<Match> = matchDetails

    private val firstTeamPlayers = MutableSharedFlow<List<Player>>()
    fun firstTeamPlayers(): SharedFlow<List<Player>> = firstTeamPlayers

    private val secondTeamPlayers = MutableSharedFlow<List<Player>>()
    fun secondTeamPlayers(): SharedFlow<List<Player>> = secondTeamPlayers

    fun init(match: Match) {
        viewModelScope.launch(mainExceptionHandler) {
            loadingState.emit(true)
            matchDetails.emit(match)
            firstTeamPlayers.emit(teamUseCase.getTeam(match.firstTeam.id.toString()))
            secondTeamPlayers.emit(teamUseCase.getTeam(match.secondTeam.id.toString()))
            loadingState.emit(false)
        }
    }
}
