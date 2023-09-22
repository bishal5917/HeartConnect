package com.example.heartconnect.navigation

import androidx.navigation.NavController

class Navigator {
    fun back(navController: NavController) {
        navController.popBackStack()
    }

    fun navigateTo(navController: NavController, destination: String) {
        navController.navigate(destination)
    }

    fun navigateOffAll(
        navController: NavController, destination: String, removeTillScreen:
        String
    ) {
        navController.navigate(destination) {
            popUpTo(removeTillScreen) {
                inclusive = true
            }
        }
    }
}