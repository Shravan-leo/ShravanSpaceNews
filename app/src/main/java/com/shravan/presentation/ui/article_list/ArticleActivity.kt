package com.shravan.presentation.ui.article_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.shravan.R
import com.shravan.navigation.SpaceCraftNavHost
import com.shravan.presentation.theme.SpaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set the status bar color
        window.statusBarColor = getColor(R.color.primary)
        setContent {
            SpaceTheme {
                setContent {
                    SpaceCraftNavHost()
                }
            }
        }
    }
}