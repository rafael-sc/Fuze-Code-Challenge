package com.orafaelsc.fuzecodechallenge.domain.repository

import com.orafaelsc.fuzecodechallenge.ui.matches.model.Player

interface TeamRepository {
    suspend fun getTeam(teamId: String): List<Player>
}
