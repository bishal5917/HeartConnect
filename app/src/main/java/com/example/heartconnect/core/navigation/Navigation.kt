package com.example.heartconnect.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.heartconnect.features.presentation.screens.splash.SplashScreen
import com.example.heartconnect.features.presentation.screens.chat.ChatScreen
import com.example.heartconnect.features.presentation.screens.home.HomeScreen
import com.example.heartconnect.features.presentation.screens.login.LoginScreen
import com.example.heartconnect.features.presentation.screens.main.MainScreen
import com.example.heartconnect.features.presentation.screens.message.MessageScreen
import com.example.heartconnect.features.presentation.screens.profile.ProfileScreen
import com.example.heartconnect.features.presentation.screens.register.RegisterScreen

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

        composable(AllScreen.RegisterScreen.name) {
            RegisterScreen(navController = navController)
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

        composable(
            route = "${AllScreen.MessageScreen.name}/{id}/{name}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            }, navArgument("name") {
                type = NavType.StringType
            })
        ) {
            MessageScreen(navController = navController, it)
        }
    }
}