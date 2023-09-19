package com.example.heartconnect.features.presentation.screens.chat.viewmodel

import com.example.heartconnect.features.data.models.conversation.ConversationModel

data class ChatState(
    val status: Status, val message: String? = null, val chats: List<ConversationModel>? = null
) {
    companion object {
        val IDLE = ChatState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}