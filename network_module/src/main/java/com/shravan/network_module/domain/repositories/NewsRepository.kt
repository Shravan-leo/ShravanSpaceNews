package com.shravan.network_module.domain.repositories

import com.shravan.core_module.utility.Result
import com.shravan.network_module.domain.models.Article

interface NewsRepository {

    //to get the articles list
    suspend fun getArticles(): Result<List<Article>>
}