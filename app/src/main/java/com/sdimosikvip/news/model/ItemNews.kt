package com.sdimosikvip.news.model

import com.sdimosikvip.news.base.BaseDiffModel

data class ItemNews(
    val urlRedirect: String,
    val urlImg: String?,
    val tittle: String,
) : BaseDiffModel {
    override val id: Long = urlRedirect.hashCode().toLong()
}
