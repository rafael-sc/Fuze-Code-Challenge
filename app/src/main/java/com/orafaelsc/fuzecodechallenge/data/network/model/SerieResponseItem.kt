package com.orafaelsc.fuzecodechallenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SerieResponseItem(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String?
)
