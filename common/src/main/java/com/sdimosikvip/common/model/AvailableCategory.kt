package com.sdimosikvip.common.model

import com.google.gson.annotations.SerializedName

enum class AvailableCategory(
    val value: String
) {
    @SerializedName("business") BUSINESS("Business \uD83D\uDCBC"),
    @SerializedName("entertainment") ENTERTAINMENT("Entertainment \uD83D\uDC40"),
    @SerializedName("general") GENERAL("General \uD83D\uDC7D"),
    @SerializedName("health") HEALTH("Health \uD83C\uDF33"),
    @SerializedName("science") SCIENCE("Science \uD83E\uDD16"),
    @SerializedName("sports") SPORTS("Sports \uD83C\uDFC5"),
    @SerializedName("technology") TECHNOLOGY("Technology \uD83D\uDCBB"),
}