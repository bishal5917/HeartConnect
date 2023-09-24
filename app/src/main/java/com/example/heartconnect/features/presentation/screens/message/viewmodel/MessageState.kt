package com.example.heartconnect.features.presentation.screens.message.viewmodel

import com.example.heartconnect.features.data.models.message.MessageModel

data class MessageState(
    val status: Status, val message: String? = null, val messages: List<MessageModel>? = null
) {
    companion object {
        val IDLE = MessageState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}