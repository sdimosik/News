package com.sdimosikvip.news.model

import com.sdimosikvip.news.base.BaseModel

data class ItemListNews(
    val list: List<BaseModel>,
    val tittle: String,
) : BaseModel
