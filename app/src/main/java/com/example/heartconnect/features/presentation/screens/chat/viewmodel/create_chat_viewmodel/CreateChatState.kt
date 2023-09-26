package com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel

data class CreateChatState(
    val status: Status, val message: String? = null,
) {
    companion object {
        val IDLE = CreateChatState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}