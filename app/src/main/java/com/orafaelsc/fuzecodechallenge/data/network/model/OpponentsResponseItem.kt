package com.orafaelsc.fuzecodechallenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpponentResponseItem(
    @field:Json(name = "opponent") val opponent: OpponentItem,
) {
    @JsonClass(generateAdapter = true)
    data class OpponentItem(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "image_url") val imageUrl: String?,
        @field:Json(name = "name") val name: String?
    )
}
