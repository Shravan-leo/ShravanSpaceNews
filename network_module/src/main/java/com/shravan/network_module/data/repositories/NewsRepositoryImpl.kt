package com.shravan.network_module.data.repositories

import com.shravan.core_module.domain.repositories.DefaultDispatcherRepository
import com.shravan.core_module.domain.repositories.NetworkStateRepo
import com.shravan.core_module.utility.ErrorType
import com.shravan.core_module.utility.Result
import com.shravan.network_module.data.api.ConnectionApi
import com.shravan.network_module.domain.mappers.ArticlesMapper.toArticlesList
import com.shravan.network_module.domain.models.Article
import com.shravan.network_module.domain.repositories.NewsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val connectionApi: ConnectionApi,
    private val dispatcherRepo: DefaultDispatcherRepository,
    private val networkStateRepo: NetworkStateRepo
) : NewsRepository {

    override suspend fun getArticles(): Result<List<Article>> = withContext(dispatcherRepo.io) {
        return@withContext try {

            //if internet is not available return error
            if (!networkStateRepo.isInternetConnected())
                return@withContext Result.Error(
                    ErrorType.NETWORK_ERROR
                )

            //trigger articles api call
            val response = connectionApi.getArticles()
            //convert response to domain model to use in view
            val articles = response.toArticlesList()
            if (articles.isEmpty()) {
                Result.Error(ErrorType.DATA_NOT_AVAILABLE)
            } else {
                Result.Success(articles)
            }
        } catch (e: Exception) {
            Result.Error(ErrorType.SERVER_ERROR)
        }
    }
}