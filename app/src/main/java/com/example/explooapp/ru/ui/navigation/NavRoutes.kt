package com.example.explooapp.ru.ui.navigation

sealed class NavRoutes(val route: String) {
    object LogInCode : NavRoutes("logInCode")
    object LogInMail : NavRoutes("logInMail")
    object Welcome : NavRoutes("Welcome")
}
