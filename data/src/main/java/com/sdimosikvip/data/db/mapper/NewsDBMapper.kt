package com.sdimosikvip.data.db.mapper

import com.sdimosikvip.common.mapper.BaseBidirectionalMapper
import com.sdimosikvip.data.db.model.NewsDB
import com.sdimosikvip.domain.model.OneNewsDomain

class NewsDBMapper : BaseBidirectionalMapper<NewsDB, OneNewsDomain> {
    override fun transform(o: NewsDB): OneNewsDomain = OneNewsDomain(
        urlRedirect = o.urlRedirect,
        urlImg = o.urlImg,
        tittle = o.tittle,
        timestamp = o.timestamp,
        description = o.description
    )


    override fun reverseTransform(o: OneNewsDomain): NewsDB = NewsDB(
        urlRedirect = o.urlRedirect,
        urlImg = o.urlImg,
        tittle = o.tittle,
        timestamp = o.timestamp,
        description = o.description
    )
}