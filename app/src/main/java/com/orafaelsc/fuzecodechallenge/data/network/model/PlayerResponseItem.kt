package com.orafaelsc.fuzecodechallenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerResponseItem(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "slug") val slug: String?,
    @field:Json(name = "image_url") val imageUrl: String?
)
