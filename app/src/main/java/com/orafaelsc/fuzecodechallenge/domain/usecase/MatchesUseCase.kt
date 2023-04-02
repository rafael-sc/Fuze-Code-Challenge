package com.orafaelsc.fuzecodechallenge.domain.usecase

import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match

interface MatchesUseCase {

    suspend fun getMatches(): List<Match>
}
