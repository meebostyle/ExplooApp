package com.example.explooapp.ru.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.compose.rememberNavController
import com.example.alfatesttask.ui.theme.ExplooTheme
import com.example.explooapp.ru.ui.navigation.ComposeNavigationManager
import com.example.explooapp.ru.ui.navigation.NavGraph
import com.example.explooapp.ru.ui.navigation.NavRoutes
import com.example.explooapp.ru.ui.navigation.NavigationManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExplooTheme {
                val navController = rememberNavController()
                val navigationManager = ComposeNavigationManager(
                    navController = navController
                )
                CompositionLocalProvider(
                    LocalNavigationManager provides navigationManager
                ) {
                    NavGraph(
                        navController = navController,
                        startDestination = NavRoutes.Welcome.route
                    )
                }
            }
        }
    }
}

val LocalNavigationManager = compositionLocalOf<NavigationManager> {
    error("No navigation manager provided")
}