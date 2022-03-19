package com.sdimosikvip.common.utils

import java.text.SimpleDateFormat
import java.util.*


@Suppress("SimpleDateFormat")
fun getTimestamp(date: String, pattern: String, timezone: String = "UTC"): Long {
    val dateFormat = SimpleDateFormat(pattern)
    dateFormat.timeZone = TimeZone.getTimeZone(timezone)
    return dateFormat.parse(date).time / 1000
}

@Suppress("SimpleDateFormat")
fun getTimestampFromNewsArticle(date: String): Long {
    val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    return getTimestamp(date, pattern)
}