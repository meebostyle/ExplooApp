package com.example.explooapp.ru.ui.navigation

import androidx.navigation.NavController

interface NavigationManager {
    fun navigateBack()
    fun navigateToLogInCode()
    fun navigateToLogInMail()
    fun navigateToWelcome()
}

class ComposeNavigationManager(
    private val navController: NavController
) : NavigationManager {
    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToLogInCode() {
        navController.navigate(NavRoutes.LogInCode.route)
    }

    override fun navigateToLogInMail() {
        navController.navigate(NavRoutes.LogInMail.route)
    }

    override fun navigateToWelcome() {
        navController.navigate(NavRoutes.Welcome.route) {
            popUpTo(0)
        }
    }


}
