package com.orafaelsc.fuzecodechallenge.domain.extension

import com.orafaelsc.fuzecodechallenge.R
import com.orafaelsc.fuzecodechallenge.commom.ResourceProvider
import com.orafaelsc.fuzecodechallenge.commom.extensions.toLocalDate
import com.orafaelsc.fuzecodechallenge.data.network.model.MatchResponseItem
import com.orafaelsc.fuzecodechallenge.data.network.model.OpponentResponseItem
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Team
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun MatchResponseItem.toDomain(resourceProvider: ResourceProvider) = Match(
    id = id,
    firstTeam = getTeam(resourceProvider, opponents.getOrNull(0)),
    secondTeam = getTeam(resourceProvider, opponents.getOrNull(1)),
    isLive = if (endAt != null) {
        false
    } else {
        isLive(
            status,
            beginAt
        )
    },
    startTime = beginAt?.toLocalDate() ?: LocalDateTime.MAX,
    description = resourceProvider.getString(
        R.string.description,
        league.name.orEmpty(),
        serie.name.orEmpty()
    ),
    starTimeText = getStartTime(resourceProvider, beginAt)
)

private fun isLive(status: String?, beginAt: String?) = when {
    status == null && beginAt == null -> false
    beginAt == null -> false
    status == "running" -> true
    else -> {
        val now = ZonedDateTime.now()
        val startTime = ZonedDateTime.parse(beginAt)
        now.isAfter(startTime)
    }
}

private fun getTeam(
    resourceProvider: ResourceProvider,
    opponentResponseItem: OpponentResponseItem?
): Team {
    return if (opponentResponseItem == null) {
        Team(
            name = resourceProvider.getString(R.string.to_be_defined),
            ""
        )
    } else {
        Team(
            name = opponentResponseItem.opponent.name
                ?: resourceProvider.getString(R.string.to_be_defined),
            iconUrl = opponentResponseItem.opponent.imageUrl.orEmpty()
        )
    }
}

private fun getStartTime(resourceProvider: ResourceProvider, beginAt: String?): String {
    return beginAt?.toLocalDate()?.run {
        val now = LocalDateTime.now()
        when {
            now.isAfter(this) -> resourceProvider.getString(R.string.now)
            now.isBefore(this) && this.isBefore(LocalDateTime.now().plusDays(1)) -> resourceProvider.getString(R.string.today)
            else -> DateTimeFormatter.ofPattern("EEE HH:mm").format(this)
        }
    } ?: ""
}
