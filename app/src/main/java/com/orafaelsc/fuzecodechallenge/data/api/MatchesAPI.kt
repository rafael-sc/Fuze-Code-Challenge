package com.orafaelsc.fuzecodechallenge.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchesAPI {

    @GET("matches")
    suspend fun getWeatherForecast(@Path("location_id") locationId: String): Response<Any>
}
