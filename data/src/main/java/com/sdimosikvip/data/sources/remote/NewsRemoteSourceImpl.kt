package com.sdimosikvip.data.sources.remote

import com.sdimosikvip.common.mapper.BaseUnidirectionalMapper
import com.sdimosikvip.common.model.*
import com.sdimosikvip.data.network.ConnectionManager
import com.sdimosikvip.data.network.NewsApiService
import com.sdimosikvip.data.network.mapper.NewsMapper
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
        val res = getResult(connectionManager) {
            newsApiService.getTopHeadLines(
                category,
                language,
                country,
            )
        }
        val mapper = (newsMapper as NewsMapper)
        return@withContext if (res is Outcome.Success) {
            Outcome.Success(
                mapper.transformManual(
                    res.requireValue(),
                    category
                )
            )
        } else Outcome.Failure(res.requireError())
    }
}