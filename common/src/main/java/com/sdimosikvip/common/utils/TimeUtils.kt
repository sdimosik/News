package com.sdimosikvip.common.utils

import java.text.SimpleDateFormat


@Suppress("SimpleDateFormat")
fun getTimestamp(date: String, pattern: String = "yyyy-MM-dd'T'HH:mm:ss"): Long {
    val dateFormat = SimpleDateFormat(pattern)
    return dateFormat.parse(date).time / 1000
}