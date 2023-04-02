package com.orafaelsc.fuzecodechallenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LeagueResponseItem(
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "image_url") val imageUrl: String?
)
