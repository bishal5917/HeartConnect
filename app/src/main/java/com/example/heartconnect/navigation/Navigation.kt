package com.example.heartconnect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.heartconnect.screens.SplashScreen
import com.example.heartconnect.screens.login.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AllScreen.SplashScreen.name) {

        composable(AllScreen.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(AllScreen.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
    }
}