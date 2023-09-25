package com.example.heartconnect.features.presentation.screens.message.viewmodel.send_message_viewmodel

data class SendMessageState(
    val status: Status, val message: String? = null, var typedMessage: String = ""
) {
    companion object {
        val IDLE = SendMessageState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}