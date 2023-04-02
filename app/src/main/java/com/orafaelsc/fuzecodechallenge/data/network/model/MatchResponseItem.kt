package com.orafaelsc.fuzecodechallenge.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchResponseItem(
    @Json(name = "begin_at") val beginAt: String,
    @Json(name = "detailed_stats") val detailedStats: Boolean,
    @Json(name = "draw") val draw: Boolean,
    @Json(name = "end_at") val endAt: Any?,
    @Json(name = "forfeit") val forfeit: Boolean,
    @Json(name = "game_advantage") val gameAdvantage: Any?,
//    @Json(name = "games") val games: List<Game>,
    @Json(name = "id") val id: Int,
    @Json(name = "league") val league: LeagueResponseItem,
    @Json(name = "league_id") val leagueId: Int,
//    @Json(name = "live") val live: Live,
    @Json(name = "match_type") val matchType: String,
    @Json(name = "modified_at") val modifiedAt: String,
    @Json(name = "name") val name: String,
    @Json(name = "number_of_games") val numberOfGames: Int,
    @Json(name = "opponents") val opponents: List<OpponentResponseItem>?,
    @Json(name = "original_scheduled_at") val originalScheduledAt: String,
    @Json(name = "rescheduled") val rescheduled: Boolean,
//    @Json(name = "results") val results: List<Result>,
    @Json(name = "scheduled_at") val scheduledAt: String,
    @Json(name = "serie") val serie: SerieResponseItem,
    @Json(name = "serie_id") val serieId: Int,
    @Json(name = "slug") val slug: String,
    @Json(name = "status") val status: String,
// //    @Json(name = "streams_list") val streamsList: List<Streams>,
// //    @Json(name = "tournament") val tournament: Tournament,
//    @Json(name = "tournament_id") val tournamentId: Int,
// //    @Json(name = "videogame") val videogame: Videogame,
//    @Json(name = "videogame_version") val videogameVersion: Any?,
//    @Json(name = "winner") val winner: Any?,
//    @Json(name = "winner_id") val winnerId: Any?,
//    @Json(name = "winner_type") val winnerType: String,
)
