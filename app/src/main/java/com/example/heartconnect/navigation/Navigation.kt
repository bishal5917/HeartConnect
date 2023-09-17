package com.example.heartconnect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.heartconnect.features.presentation.screens.splash.SplashScreen
import com.example.heartconnect.features.presentation.screens.chat.ChatScreen
import com.example.heartconnect.features.presentation.screens.home.HomeScreen
import com.example.heartconnect.features.presentation.screens.login.LoginScreen
import com.example.heartconnect.features.presentation.screens.main.MainScreen
import com.example.heartconnect.features.presentation.screens.profile.ProfileScreen

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

        composable(AllScreen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(AllScreen.ChatScreen.name) {
            ChatScreen(navController = navController)
        }

        composable(AllScreen.ProfileScreen.name) {
            ProfileScreen(navController = navController)
        }

        composable(AllScreen.MainScreen.name) {
            MainScreen(navController = navController)
        }
    }
}