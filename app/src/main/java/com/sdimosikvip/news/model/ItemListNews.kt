package com.sdimosikvip.news.model

import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.news.base.BaseDiffModel

data class ItemListNews(
    var list: List<BaseDiffModel>,
    val tittle: String,
    val category: AvailableCategory,
) : BaseDiffModel {
    override val id: Long = tittle.hashCode().toLong()
}
