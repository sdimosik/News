package com.sdimosikvip.domain.interactor

import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.common.model.AvailableCountry
import com.sdimosikvip.common.model.AvailableLanguage
import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.repository.NewsRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


interface NewsInteractor {
    suspend fun getOneHotNews(
        category: AvailableCategory,
        language: AvailableLanguage = AvailableLanguage.EN,
        country: AvailableCountry? = null,
    ): NewsDomain

    suspend fun getAllHotNews(
        language: AvailableLanguage = AvailableLanguage.EN,
        country: AvailableCountry? = null,
    ): List<NewsDomain>
}

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {
    override suspend fun getOneHotNews(
        category: AvailableCategory,
        language: AvailableLanguage,
        country: AvailableCountry?
    ): NewsDomain {
        return newsRepository.getTopHeadLines(category, language, country)
    }

    override suspend fun getAllHotNews(
        language: AvailableLanguage,
        country: AvailableCountry?
    ): List<NewsDomain> {

        val listDef: MutableList<Deferred<NewsDomain>> = mutableListOf()
        return coroutineScope {
            AvailableCategory.values()
                .sortedBy { it.value }
                .forEach {
                    val deferred = async {
                        val response = newsRepository.getTopHeadLines(it, language, country)
                        response.list = response.list.sortedByDescending { it.timestamp }
                        response.category = it
                        return@async response
                    }
                    synchronized(listDef) {
                        listDef.add(deferred)
                    }
                }

            return@coroutineScope listDef.awaitAll()
        }
    }
}