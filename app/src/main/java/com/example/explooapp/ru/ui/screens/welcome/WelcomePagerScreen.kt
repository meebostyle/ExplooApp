package com.example.explooapp.ru.ui.screens.welcome

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.explooapp.ru.ui.screens.welcome.pages.GreetingPage

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "first_screen"
    ) {
        composable("first_screen") {
            GreetingPage()
        }
    }
}