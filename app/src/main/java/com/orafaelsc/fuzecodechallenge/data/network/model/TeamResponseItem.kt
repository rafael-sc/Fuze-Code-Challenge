package com.orafaelsc.fuzecodechallenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamResponseItem(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "players") val players: List<PlayerResponseItem>?
)
