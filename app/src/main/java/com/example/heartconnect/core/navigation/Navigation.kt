package com.example.heartconnect.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.heartconnect.features.presentation.screens.splash.SplashScreen
import com.example.heartconnect.features.presentation.screens.chat.ChatScreen
import com.example.heartconnect.features.presentation.screens.feed.FeedScreen
import com.example.heartconnect.features.presentation.screens.feed.components.single_feed.SingleFeedScreen
import com.example.heartconnect.features.presentation.screens.login.LoginScreen
import com.example.heartconnect.features.presentation.screens.main.MainScreen
import com.example.heartconnect.features.presentation.screens.message.MessageScreen
import com.example.heartconnect.features.presentation.screens.profile.ProfileScreen
import com.example.heartconnect.features.presentation.screens.profile.components.add_picture.AddPictureScreen
import com.example.heartconnect.features.presentation.screens.profile.components.change_password.ChangePasswordScreen
import com.example.heartconnect.features.presentation.screens.profile.components.change_picture.ChangePictureScreen
import com.example.heartconnect.features.presentation.screens.profile.components.my_pic.MyPicsScreen
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
            FeedScreen(navController = navController)
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

        composable(AllScreen.ChangePasswordScreen.name) {
            ChangePasswordScreen(navController = navController)
        }

        composable(AllScreen.ChangePictureScreen.name) {
            ChangePictureScreen(navController = navController)
        }

        composable(AllScreen.AddPictureScreen.name) {
            AddPictureScreen(navController = navController)
        }

        composable(AllScreen.MyPicsScreen.name) {
            MyPicsScreen(navController = navController)
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

        composable(
            route = "${AllScreen.SingleFeedScreen.name}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            SingleFeedScreen(navController = navController, it)
        }
    }
}