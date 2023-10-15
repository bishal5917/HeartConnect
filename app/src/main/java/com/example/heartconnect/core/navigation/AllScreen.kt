package com.example.heartconnect.core.navigation


enum class AllScreen {
    SplashScreen, LoginScreen, RegisterScreen, MainScreen, HomeScreen, ChatScreen, ProfileScreen,
    ChangePasswordScreen, MessageScreen, ChangePictureScreen;

    companion object {
        fun fromRoute(route: String?): AllScreen = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            MainScreen.name -> MainScreen
            HomeScreen.name -> HomeScreen
            ChatScreen.name -> ChatScreen
            ProfileScreen.name -> ProfileScreen
            ChangePasswordScreen.name -> ChangePasswordScreen
            ChangePictureScreen.name -> ChangePictureScreen
            "${MessageScreen.name}/{id}/{name}" -> MessageScreen
            null -> LoginScreen
            else -> throw IllegalArgumentException("Invalid Route")
        }
    }
}

//sealed class AllScreen (val route : String){
//  object HomeScreen : AllScreen("home_screen")
//}