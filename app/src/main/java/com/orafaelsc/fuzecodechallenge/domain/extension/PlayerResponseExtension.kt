package com.orafaelsc.fuzecodechallenge.domain.extension

import com.orafaelsc.fuzecodechallenge.data.network.model.PlayerResponseItem
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Player

fun PlayerResponseItem.toDomain() = Player(
    id = id,
    name = name,
    image = imageUrl,
    nickName = slug
)
