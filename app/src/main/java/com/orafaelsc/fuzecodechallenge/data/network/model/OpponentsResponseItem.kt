package com.orafaelsc.fuzecodechallenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpponentResponseItem(
    @Json(name = "acronym") val acronym: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "image_url") val imageUrl: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "modified_at") val modifiedAt: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "slug") val slug: String?,
)
