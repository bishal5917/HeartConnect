package com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel

import com.example.heartconnect.features.data.models.chat.ChatRequestModel

sealed class CreateChatEvent {
    data class CreateChat(val chatRequestModel: ChatRequestModel) : CreateChatEvent()
}