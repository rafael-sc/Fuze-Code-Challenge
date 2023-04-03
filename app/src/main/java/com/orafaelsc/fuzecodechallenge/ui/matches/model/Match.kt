package com.orafaelsc.fuzecodechallenge.ui.matches.model

import android.os.Parcelable
import java.time.LocalDateTime
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    val id: Int,
    val firstTeam: Team,
    val secondTeam: Team,
    val startTime: LocalDateTime,
    val description: String,
    val starTimeText: String,
    val leagueLogo: String
) : Parcelable
