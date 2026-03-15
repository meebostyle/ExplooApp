package com.example.explooapp.ru.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.explooapp.ru.ui.screens.auth.logincode.LogInCodeScreen
import com.example.explooapp.ru.ui.screens.auth.loginmail.LogInMailScreen
import com.example.explooapp.ru.ui.screens.auth.welcome.WelcomeScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        composable(NavRoutes.LogInMail.route) {
            LogInMailScreen()
        }
        composable(NavRoutes.LogInCode.route) {
            LogInCodeScreen()
        }
        composable(NavRoutes.Welcome.route) {
            WelcomeScreen()
        }

    }
}
