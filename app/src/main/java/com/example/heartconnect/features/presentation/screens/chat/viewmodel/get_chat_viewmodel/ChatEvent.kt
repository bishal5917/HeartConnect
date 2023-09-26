package com.example.heartconnect.features.presentation.screens.chat.viewmodel.get_chat_viewmodel

sealed class ChatEvent {
    data class GetChats(val userId: String) : ChatEvent()
}