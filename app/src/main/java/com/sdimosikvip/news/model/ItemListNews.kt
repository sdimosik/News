package com.sdimosikvip.news.model

import com.sdimosikvip.news.base.BaseDiffModel

data class ItemListNews(
    val list: List<BaseDiffModel>,
    val tittle: String,
) : BaseDiffModel {
    override val id: Long = tittle.hashCode().toLong()
}
