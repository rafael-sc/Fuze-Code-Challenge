package com.orafaelsc.fuzecodechallenge.data

import com.orafaelsc.fuzecodechallenge.commom.ResourceProvider
import com.orafaelsc.fuzecodechallenge.commom.exceptions.ApiException
import com.orafaelsc.fuzecodechallenge.data.network.MatchesApi
import com.orafaelsc.fuzecodechallenge.domain.extension.toDomain
import com.orafaelsc.fuzecodechallenge.domain.repository.MatchesRepository
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match

class MatchesRepositoryImpl(
    private val matchesAPI: MatchesApi,
    private val resourceProvider: ResourceProvider
) : MatchesRepository {
    override suspend fun getMatches(): List<Match> {
        val result = matchesAPI.getMatches()
        if (result.isSuccessful) {
            val resultList = mutableListOf<Match>()
            result.body()?.map {
                if (it.endAt == null) {
                    resultList.add(it.toDomain(resourceProvider))
                }
            }
            return resultList.sortedBy { it.startTime }
        } else {
            throw ApiException.UnableToGetMatchesException()
        }
    }

    override suspend fun getMatch(matchId: String): Any {
        return matchesAPI.getMatch(matchId)
    }
}
