package com.sdimosikvip.common.model

import com.google.gson.annotations.SerializedName

enum class AvailableLanguage(
    val value: String
) {
    @SerializedName("ar") AR("ar"),
    @SerializedName("de") DE("de"),
    @SerializedName("en") EN("en"),
    @SerializedName("es") ES("es"),
    @SerializedName("fr") FR("fr"),
    @SerializedName("he") HE("he"),
    @SerializedName("it") IT("it"),
    @SerializedName("nl") NL("nl"),
    @SerializedName("no") NO("no"),
    @SerializedName("pt") PT("pt"),
    @SerializedName("ru") RU("ru"),
    @SerializedName("se") SE("se"),
    @SerializedName("ud") UD("ud"),
    @SerializedName("zh") ZH("zh"),
}