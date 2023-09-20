package com.example.heartconnect.features.presentation.screens.main.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.heartconnect.features.presentation.screens.chat.ChatScreen
import com.example.heartconnect.features.presentation.screens.home.HomeScreen
import com.example.heartconnect.features.presentation.screens.login.LoginScreen
import com.example.heartconnect.features.presentation.screens.profile.ProfileScreen
import com.example.heartconnect.navigation.AllScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Chat.route) {
            ChatScreen(navController)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}