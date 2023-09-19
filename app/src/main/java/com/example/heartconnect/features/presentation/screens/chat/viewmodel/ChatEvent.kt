package com.example.heartconnect.features.presentation.screens.chat.viewmodel

import com.example.heartconnect.features.presentation.screens.login.LoginEvent

sealed class ChatEvent {
    data class GetChats(val userId: String) : ChatEvent()

}