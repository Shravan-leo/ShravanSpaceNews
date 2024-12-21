package com.shravan.network_module.data.api

import com.shravan.network_module.data.dto.ApiResponseDto
import com.shravan.network_module.data.dto.ArticleDto
import retrofit2.http.GET

interface ConnectionApi {
    //to get the articles of a news
    @GET("articles")
    suspend fun getArticles(): ApiResponseDto<List<ArticleDto>?>
}