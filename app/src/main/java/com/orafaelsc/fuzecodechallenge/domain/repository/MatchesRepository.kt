package com.orafaelsc.fuzecodechallenge.domain.repository

import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match

interface MatchesRepository {
    suspend fun getMatches(): List<Match>
    suspend fun getMatch(matchId: String): Any
}
