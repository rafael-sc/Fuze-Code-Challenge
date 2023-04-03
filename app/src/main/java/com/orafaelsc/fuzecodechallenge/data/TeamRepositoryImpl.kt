package com.orafaelsc.fuzecodechallenge.data

import com.orafaelsc.fuzecodechallenge.commom.exceptions.ApiException
import com.orafaelsc.fuzecodechallenge.data.network.TeamApi
import com.orafaelsc.fuzecodechallenge.domain.extension.toDomain
import com.orafaelsc.fuzecodechallenge.domain.repository.TeamRepository
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Player

class TeamRepositoryImpl(
    private val teamApi: TeamApi
) : TeamRepository {
    override suspend fun getTeam(teamId: String): List<Player> {
        val result = teamApi.getTeam(teamId)
        if (result.isSuccessful) {
            val resultList = mutableListOf<Player>()
            result.body()?.players?.map {
                resultList.add(it.toDomain())
            }
            return resultList
        } else {
            throw ApiException.UnableToGetPlayersException()
        }
    }
}
