package com.sdimosikvip.data.sources.remote

import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.common.model.AvailableCountry
import com.sdimosikvip.common.model.AvailableLanguage
import com.sdimosikvip.common.model.Outcome
import com.sdimosikvip.domain.model.NewsDomain
import com.sdimosikvip.domain.model.OneNewsDomain

interface NewsRemoteSource {

    suspend fun getTopHeadLines(
        category: AvailableCategory,
        language: AvailableLanguage,
        country: AvailableCountry?,
    ): Outcome<NewsDomain>
}