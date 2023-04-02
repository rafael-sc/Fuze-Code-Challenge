package com.orafaelsc.fuzecodechallenge.data.network

import com.orafaelsc.fuzecodechallenge.data.network.model.MatchResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface MatchesApi {

    @GET("matches??filter\\[finished\\]=false")
    suspend fun getMatches(): Response<List<MatchResponseItem>>
}
