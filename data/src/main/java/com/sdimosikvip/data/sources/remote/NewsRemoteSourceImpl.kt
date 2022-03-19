package com.sdimosikvip.data.sources.remote

import com.sdimosikvip.common.mapper.BaseUnidirectionalMapper
import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.common.model.AvailableCountry
import com.sdimosikvip.common.model.AvailableLanguage
import com.sdimosikvip.common.model.Outcome
import com.sdimosikvip.data.network.ConnectionManager
import com.sdimosikvip.data.network.NewsApiService
import com.sdimosikvip.data.network.model.NewsResponse
import com.sdimosikvip.domain.model.NewsDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRemoteSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsMapper: BaseUnidirectionalMapper<NewsResponse, NewsDomain>,
    private val connectionManager: ConnectionManager,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsRemoteSource, BaseRemoteSource() {

    override suspend fun getTopHeadLines(
        category: AvailableCategory,
        language: AvailableLanguage,
        country: AvailableCountry?
    ): Outcome<NewsDomain> = withContext(defaultDispatcher) {
        getResult(connectionManager, newsMapper) {
            newsApiService.getTopHeadLines(
                category,
                language,
                country,
            )
        }
    }
}