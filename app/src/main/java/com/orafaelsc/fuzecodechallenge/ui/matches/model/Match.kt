package com.orafaelsc.fuzecodechallenge.ui.matches.model

import java.time.LocalDateTime

data class Match(
    val id: Int,
    val firstTeam: Team,
    val secondTeam: Team,
    val startTime: LocalDateTime,
    val description: String,
    val starTimeText: String,
    val leagueLogo: String
)
