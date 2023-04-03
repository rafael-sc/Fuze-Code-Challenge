package com.orafaelsc.fuzecodechallenge.data

import com.orafaelsc.fuzecodechallenge.commom.ResourceProvider
import com.orafaelsc.fuzecodechallenge.data.network.TeamApi
import com.orafaelsc.fuzecodechallenge.domain.repository.TeamRepository

class TeamRepositoryImpl(
    private val teamApi: TeamApi,
    private val resourceProvider: ResourceProvider
) : TeamRepository {
    override suspend fun getTeam(teamId: String): Any {
        return teamApi.getTeam(teamId)
    }
}
