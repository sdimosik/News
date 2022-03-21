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
        page: Int,
        pageSize: Int,
        category: AvailableCategory,
        language: AvailableLanguage = AvailableLanguage.EN,
        country: AvailableCountry? = null,
    ): NewsDomain

    suspend fun initNews(
        language: AvailableLanguage = AvailableLanguage.EN,
        country: AvailableCountry? = null,
    ): MutableList<NewsDomain>
}

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {
    override suspend fun getOneHotNews(
        page: Int,
        pageSize: Int,
        category: AvailableCategory,
        language: AvailableLanguage,
        country: AvailableCountry?
    ): NewsDomain {
        return newsRepository.getTopHeadLines(false, page, pageSize, category, language, country)
    }

    override suspend fun initNews(
        language: AvailableLanguage,
        country: AvailableCountry?
    ): MutableList<NewsDomain> {

        val listDef: MutableList<Deferred<NewsDomain>> = mutableListOf()
        return coroutineScope {
            AvailableCategory.values()
                .sortedBy { it.value }
                .forEach {
                    val deferred = async {
                        val response =
                            newsRepository.getTopHeadLines(true, 1, 20, it, language, country)
                        response.list = response.list.sortedByDescending { it.timestamp }
                        response.category = it
                        return@async response
                    }
                    synchronized(listDef) {
                        listDef.add(deferred)
                    }
                }
            val res = mutableListOf<NewsDomain>()
            res.addAll(
                listDef.awaitAll().filter { it.list.isNotEmpty() })
            return@coroutineScope res
        }
    }
}