package com.sdimosikvip.news.mapper

import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.model.OneNewsDomain
import com.sdimosikvip.news.model.ItemListNews
import com.sdimosikvip.news.model.ItemNews

fun newsDomainToItemNews(newsDomain: NewsDomain, tittle: String): ItemListNews = ItemListNews(
    list = newsDomain.list.map { oneNewsDomainToItemNews(it) },
    tittle = tittle
)

fun oneNewsDomainToItemNews(oneNewsDomain: OneNewsDomain): ItemNews = ItemNews(
    urlRedirect = oneNewsDomain.tittle,
    urlImg = oneNewsDomain.urlImg,
    tittle = oneNewsDomain.tittle,
    timestamp = oneNewsDomain.timestamp
)