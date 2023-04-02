package com.orafaelsc.fuzecodechallenge.data.api

import retrofit2.Response
import retrofit2.http.GET

interface MatchesApi {

    @GET("matches")
    suspend fun getMatches(): Response<Any>
}
