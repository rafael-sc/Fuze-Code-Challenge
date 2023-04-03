package com.orafaelsc.fuzecodechallenge.ui.matches.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val id: Int,
    val name: String,
    val iconUrl: String?
) : Parcelable
