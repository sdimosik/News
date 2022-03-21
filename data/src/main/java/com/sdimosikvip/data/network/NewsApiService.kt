package com.sdimosikvip.data.network

import com.sdimosikvip.common.model.AvailableCategory
import com.sdimosikvip.common.model.AvailableCountry
import com.sdimosikvip.common.model.AvailableLanguage
import com.sdimosikvip.data.network.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadLines(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("category") category: AvailableCategory,
        @Query("language") language: AvailableLanguage,
        @Query("country") country: AvailableCountry?,
    ): Response<NewsResponse>
}