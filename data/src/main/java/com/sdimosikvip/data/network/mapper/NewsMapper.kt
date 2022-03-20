package com.sdimosikvip.data.network.mapper

import com.sdimosikvip.common.mapper.BaseUnidirectionalMapper
import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.data.network.model.Article
import com.sdimosikvip.data.network.model.NewsResponse
import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.model.OneNewsDomain
import javax.inject.Inject

class NewsMapper @Inject constructor(
    private val mapper: BaseUnidirectionalMapper<Article, OneNewsDomain>
) : BaseUnidirectionalMapper<NewsResponse, NewsDomain> {
    override fun transform(o: NewsResponse): NewsDomain = NewsDomain(
        list = o.articles.map { mapper.transform(it) },
        category = AvailableCategory.BUSINESS
    )

    fun transformManual(o: NewsResponse, category: AvailableCategory): NewsDomain = NewsDomain(
        list = o.articles.map { mapper.transform(it) },
        category = category
    )
}