package com.sdimosikvip.news.mapper

import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.model.OneNewsDomain
import com.sdimosikvip.news.model.ItemListNews
import com.sdimosikvip.news.model.ItemNews
import com.sdimosikvip.news.utils.formatDayMonthTime

fun newsDomainToItemNews(newsDomain: NewsDomain): ItemListNews = ItemListNews(
    list = newsDomain.list.map { oneNewsDomainToItemNews(it) },
    tittle = newsDomain.category.value
)

fun oneNewsDomainToItemNews(oneNewsDomain: OneNewsDomain): ItemNews = ItemNews(
    urlRedirect = oneNewsDomain.urlRedirect,
    urlImg = oneNewsDomain.urlImg,
    tittle = oneNewsDomain.tittle,
    timestamp = oneNewsDomain.timestamp,
    timestampString = formatDayMonthTime(oneNewsDomain.timestamp),
    description = oneNewsDomain.description
)