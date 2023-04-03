package com.orafaelsc.fuzecodechallenge.data.network

import com.orafaelsc.fuzecodechallenge.data.network.model.TeamResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamApi {
    @GET("teams/{team_id}")
    suspend fun getTeam(@Path("team_id") teamId: String): Response<TeamResponseItem>
}
