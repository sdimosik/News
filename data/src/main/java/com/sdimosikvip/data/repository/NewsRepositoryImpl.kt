package com.sdimosikvip.data.repository

import com.sdimosikvip.common.model.*
import com.sdimosikvip.data.sources.remote.NewsRemoteSource
import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteSource: NewsRemoteSource
) : NewsRepository {
    override suspend fun getTopHeadLines(
        category: AvailableCategory,
        language: AvailableLanguage,
        country: AvailableCountry?
    ): NewsDomain {
        return newsRemoteSource.getTopHeadLines(category, language, country).requireValue()
    }
}