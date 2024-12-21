package com.shravan.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.shravan.presentation.ui.article_details.ArticleDetailsView
import com.shravan.presentation.view_models.NewsViewModel

//article details routing
@Composable
fun ArticleDetailsScreenRoute(
    newsViewModel: NewsViewModel,
    navController: NavHostController
) {
    // article detail screen
    ArticleDetailsView(newsViewModel = newsViewModel, navController = navController)
}