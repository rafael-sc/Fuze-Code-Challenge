package com.orafaelsc.fuzecodechallenge.domain.usecase

interface TeamUseCase {
    suspend fun getTeam(teamId: String): Any
}
