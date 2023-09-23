package com.example.heartconnect.features.presentation.screens.splash.viewmodel

data class SplashState(
    val status: SplashStatus, val message: String? = null,
) {
    companion object {
        val IDLE = SplashState(SplashStatus.IDLE, message = "Idle")
    }

    enum class SplashStatus {
        IDLE, LOADING , LOGGEDIN, LOGGEDOUT , FAILED
    }
}