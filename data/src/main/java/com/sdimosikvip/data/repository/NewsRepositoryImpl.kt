package com.sdimosikvip.data.repository

import com.sdimosikvip.common.mapper.BaseBidirectionalMapper
import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.common.model.AvailableCountry
import com.sdimosikvip.common.model.AvailableLanguage
import com.sdimosikvip.common.model.requireValue
import com.sdimosikvip.data.db.model.NewsDB
import com.sdimosikvip.data.network.ConnectionManager
import com.sdimosikvip.data.sources.local.NewsLocalSource
import com.sdimosikvip.data.sources.remote.NewsRemoteSource
import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.model.OneNewsDomain
import com.sdimosikvip.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDBMapper: BaseBidirectionalMapper<NewsDB, OneNewsDomain>,
    private val remoteSource: NewsRemoteSource,
    private val localSource: NewsLocalSource,
    private val connection: ConnectionManager
) : NewsRepository {

    override suspend fun getTopHeadLines(
        goCache: Boolean,
        page: Int,
        pageSize: Int,
        category: AvailableCategory,
        language: AvailableLanguage,
        country: AvailableCountry?
    ): NewsDomain {

        if (!connection.isConnected()) {
            val localListCache =
                localSource.getNewsByCategory(category).map { newsDBMapper.transform(it) }
            return NewsDomain(localListCache, category)
        }

        val newsDomain =
            remoteSource.getTopHeadLines(page, pageSize, category, language, country).requireValue()
        newsDomain.list.forEach {
            localSource.cacheNews(newsDBMapper.reverseTransform(it), newsDomain.category)
        }
        localSource.deleteManual()
        return newsDomain
    }
}