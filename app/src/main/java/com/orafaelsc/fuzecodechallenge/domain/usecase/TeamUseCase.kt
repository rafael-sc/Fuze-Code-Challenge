package com.orafaelsc.fuzecodechallenge.domain.usecase

import com.orafaelsc.fuzecodechallenge.ui.matches.model.Player

interface TeamUseCase {
    suspend fun getTeam(teamId: String): List<Player>
}
