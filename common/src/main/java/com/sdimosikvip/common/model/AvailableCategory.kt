package com.sdimosikvip.common.model

import com.google.gson.annotations.SerializedName

enum class AvailableCategory(
    val value: String
) {
    @SerializedName("business") BUSINESS("business"),
    @SerializedName("entertainment") ENTERTAINMENT("entertainment"),
    @SerializedName("general") GENERAL("general"),
    @SerializedName("health") HEALTH("health"),
    @SerializedName("science") SCIENCE("science"),
    @SerializedName("sports") SPORTS("sports"),
    @SerializedName("technology") TECHNOLOGY("technology"),
}