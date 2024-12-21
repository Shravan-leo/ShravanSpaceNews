package com.shravan.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.shravan.presentation.ui.article_list.ArticleListView
import com.shravan.presentation.view_models.NewsViewModel

@Composable
fun ArticleListScreenRoute(
    viewModel: NewsViewModel,
    navController: NavHostController,
) {

    // article list screen
    ArticleListView(navController = navController, newsViewModel = viewModel)
}