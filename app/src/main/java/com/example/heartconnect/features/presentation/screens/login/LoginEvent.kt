package com.example.heartconnect.features.presentation.screens.login

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()

    object LoginUser : LoginEvent()
    object LogoutUser : LoginEvent()

}