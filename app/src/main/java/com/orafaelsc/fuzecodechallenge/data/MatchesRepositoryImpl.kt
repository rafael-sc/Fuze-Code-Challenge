package com.orafaelsc.fuzecodechallenge.data

import com.orafaelsc.fuzecodechallenge.commom.ResourceProvider
import com.orafaelsc.fuzecodechallenge.commom.exceptions.ApiException
import com.orafaelsc.fuzecodechallenge.data.network.MatchesApi
import com.orafaelsc.fuzecodechallenge.domain.repository.MatchesRepository

class MatchesRepositoryImpl(
    private val matchesAPI: MatchesApi,
    private val resourceProvider: ResourceProvider,
) : MatchesRepository {
    override suspend fun getMatches(): Any {
        val result = matchesAPI.getMatches()
        if (result.isSuccessful) {
            return result.body() // toDomain
                ?: throw ApiException.UnableToGetMatchesException() //
        } else {
            throw ApiException.UnableToGetMatchesException()
        }
    }
}
