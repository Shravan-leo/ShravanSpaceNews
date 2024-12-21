package com.shravan.domain.use_cases

import com.shravan.core_module.utility.Result
import com.shravan.network_module.domain.models.Article
import com.shravan.network_module.domain.repositories.NewsRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    /*to fetch the articles from the api*/
    suspend fun getArticles(): Result<List<Article>> {
        return newsRepository.getArticles()
    }
}