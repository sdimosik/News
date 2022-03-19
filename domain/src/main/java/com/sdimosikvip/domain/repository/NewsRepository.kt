package com.sdimosikvip.domain.repository

import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.common.model.AvailableCountry
import com.sdimosikvip.common.model.AvailableLanguage
import com.sdimosikvip.common.model.Outcome
import com.sdimosikvip.domain.model.NewsDomain

interface NewsRepository {

    suspend fun getTopHeadLines(
        category: AvailableCategory,
        language: AvailableLanguage,
        country: AvailableCountry?,
    ): NewsDomain
}