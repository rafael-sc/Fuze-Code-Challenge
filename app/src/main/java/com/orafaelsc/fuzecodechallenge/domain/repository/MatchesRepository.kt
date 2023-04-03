package com.orafaelsc.fuzecodechallenge.domain.repository

import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match

interface MatchesRepository {
    suspend fun getMatches(): List<Match>
}
