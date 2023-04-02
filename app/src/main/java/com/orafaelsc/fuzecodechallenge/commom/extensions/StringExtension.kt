package com.orafaelsc.fuzecodechallenge.commom.extensions

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDateTime? {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val data = LocalDateTime.parse(this, formatter)
    return data.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
}
