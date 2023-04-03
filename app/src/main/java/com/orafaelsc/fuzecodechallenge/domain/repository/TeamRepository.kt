package com.orafaelsc.fuzecodechallenge.domain.repository

interface TeamRepository {
    suspend fun getTeam(teamId: String): Any
}
