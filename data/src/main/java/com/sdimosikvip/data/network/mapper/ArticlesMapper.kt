package com.sdimosikvip.data.network.mapper

import com.sdimosikvip.common.mapper.BaseUnidirectionalMapper
import com.sdimosikvip.common.utils.getTimestampFromNewsArticle
import com.sdimosikvip.data.network.model.Article
import com.sdimosikvip.domain.model.OneNewsDomain

class ArticlesMapper : BaseUnidirectionalMapper<Article, OneNewsDomain> {
    override fun transform(o: Article): OneNewsDomain = OneNewsDomain(
        urlRedirect = o.url,
        urlImg = o.urlToImage,
        tittle = o.title,
        timestamp = getTimestampFromNewsArticle(o.publishedAt)
    )
}