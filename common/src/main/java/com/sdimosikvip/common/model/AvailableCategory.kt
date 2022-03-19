package com.sdimosikvip.common.model

import com.google.gson.annotations.SerializedName

enum class AvailableCategory(
    val value: String
) {
    @SerializedName("business") BUSINESS("Business"),
    @SerializedName("entertainment") ENTERTAINMENT("Entertainment"),
    @SerializedName("general") GENERAL("General"),
    @SerializedName("health") HEALTH("Health"),
    @SerializedName("science") SCIENCE("Science"),
    @SerializedName("sports") SPORTS("Sports"),
    @SerializedName("technology") TECHNOLOGY("Technology"),
}