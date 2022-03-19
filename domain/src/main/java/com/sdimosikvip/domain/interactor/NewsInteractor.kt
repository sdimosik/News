package com.sdimosikvip.domain.interactor

import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.common.model.AvailableCountry
import com.sdimosikvip.common.model.AvailableLanguage
import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.repository.NewsRepository
import javax.inject.Inject


interface NewsInteractor {
    suspend fun getTopHeadLines(
        category: AvailableCategory,
        language: AvailableLanguage = AvailableLanguage.EN,
        country: AvailableCountry? = null,
    ): NewsDomain
}

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {
    override suspend fun getTopHeadLines(
        category: AvailableCategory,
        language: AvailableLanguage,
        country: AvailableCountry?
    ): NewsDomain {
        return newsRepository.getTopHeadLines(category, language, country)
    }
}