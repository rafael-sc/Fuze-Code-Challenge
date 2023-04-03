package com.orafaelsc.fuzecodechallenge.domain.usecase

import com.orafaelsc.fuzecodechallenge.domain.repository.TeamRepository
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Player

class TeamUseCaseImpl(private val teamRepository: TeamRepository) : TeamUseCase {
    override suspend fun getTeam(teamId: String): List<Player> {
        return teamRepository.getTeam(teamId)
    }
}
