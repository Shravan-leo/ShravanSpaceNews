package com.shravan.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shravan.presentation.view_models.NewsViewModel
import com.shravan.route.ArticleDetailsScreenRoute
import com.shravan.route.ArticleListScreenRoute


//nav graph builder for various routes
fun NavGraphBuilder.articleListRoute(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
) {
    //article listing
    composable(route = Routes.ARTICLE_LIST_SCREEN) {
        ArticleListScreenRoute(
            viewModel = newsViewModel,
            navController = navController
        )
    }
}


fun NavGraphBuilder.articleDetailsRoute(
    newsViewModel: NewsViewModel,
    navController: NavHostController
) {
    //article details
    composable(route = Routes.ARTICLE_DETAILS_SCREEN) {
        ArticleDetailsScreenRoute(
            newsViewModel = newsViewModel,
            navController = navController
        )
    }
}

