package com.sdimosikvip.news.model

import com.sdimosikvip.news.base.BaseModel

data class ItemNews(
    val urlBase: String,
    val urlImg: String?,
    val tittle: String,
) : BaseModel {
    val id: String = urlBase
}
