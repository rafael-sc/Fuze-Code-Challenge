package com.orafaelsc.fuzecodechallenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchResponseItem(
    @field:Json(name = "begin_at") val beginAt: String?,
    @field:Json(name = "end_at") val endAt: String?,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "league") val league: LeagueResponseItem,
    @field:Json(name = "league_id") val leagueId: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "opponents") val opponents: List<OpponentResponseItem>,
    @field:Json(name = "serie") val serie: SerieResponseItem,
    @field:Json(name = "slug") val slug: String,
    @field:Json(name = "status") val status: String,
)
