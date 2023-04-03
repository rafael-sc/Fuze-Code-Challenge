package com.orafaelsc.fuzecodechallenge.domain.usecase

import com.orafaelsc.fuzecodechallenge.domain.repository.TeamRepository

class TeamUseCaseImpl(private val teamRepository: TeamRepository) : TeamUseCase {
    override suspend fun getTeam(teamId: String): Any {
        return teamRepository.getTeam(teamId)
    }
}
