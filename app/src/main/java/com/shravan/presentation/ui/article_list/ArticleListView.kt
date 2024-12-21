package com.shravan.presentation.ui.article_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.shravan.R
import com.shravan.core_module.utility.Result
import com.shravan.navigation.Routes
import com.shravan.network_module.domain.models.Article
import com.shravan.presentation.components.CustomAppBar
import com.shravan.presentation.components.ErrorView
import com.shravan.presentation.utility.getErrorMessage
import com.shravan.presentation.view_models.NewsViewModel


@Composable
fun ArticleListView(
    newsViewModel: NewsViewModel,
    navController: NavController,

    ) {
    val articlesResult by newsViewModel.articles.collectAsState(initial = Result.Loading) // article results

    val context = LocalContext.current // current context
    var isRefreshing by remember { mutableStateOf(false) } // refresh state

    Scaffold(
        //app bar
        topBar = {
            CustomAppBar(
                title = stringResource(id = R.string.app_name),
                isToShowRefreshIcon = true,
                //handling of refresh button
                onRefreshClick = {
                    isRefreshing = true //to show loading when refresh is clicked
                    newsViewModel.fetchArticles()
                },
                onBackButtonClick = {},
            )

        }
    ) { paddingValues ->
        //handling various states
        when (articlesResult) {
            is Result.Loading -> {
                isRefreshing = true
            }

            is Result.Success -> {
                isRefreshing = false
                val articlesList = (articlesResult as Result.Success<List<Article>>).data
                //showing article list
                ArticleList(
                    articles = articlesList,
                    modifier = Modifier.padding(paddingValues),
                    onArticleClick = { article ->
                        newsViewModel.setSelectedArticle(article) // set the selected article
                        navController.navigate(Routes.ARTICLE_DETAILS_SCREEN) // navigate to details screen
                    }
                )

            }

            is Result.Error -> {
                isRefreshing = false // hide progress if error occurs
                val errorType = (articlesResult as Result.Error).message
                val errorMessage =
                    getErrorMessage(mContext = context, errorType = errorType) // manage errors
                //error state handling
                ErrorView(message = errorMessage,
                    //on retry click fetch the articles from server
                    onRetry = {
                        isRefreshing = true
                        newsViewModel.fetchArticles()
                    })
            }
        }

        //based on condition show the shimmer effect
        if (isRefreshing) {
            ArticleListShimmer(
                modifier = Modifier.padding(paddingValues),
            )
        }

    }

}


