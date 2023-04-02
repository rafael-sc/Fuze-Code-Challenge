package com.orafaelsc.fuzecodechallenge.domain.repository


interface MatchesRepository {
    suspend fun getMatches(): Any // viewObject
}
