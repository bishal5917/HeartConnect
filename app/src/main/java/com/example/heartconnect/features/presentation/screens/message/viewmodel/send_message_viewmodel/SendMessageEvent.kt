package com.example.heartconnect.features.presentation.screens.message.viewmodel.send_message_viewmodel

import com.example.heartconnect.features.data.models.message.MessageRequestModel

sealed class SendMessageEvent {
    data class MessageTyping(val typedMessage: String) : SendMessageEvent()

    data class SendMessage(val messageRequestModel: MessageRequestModel) : SendMessageEvent()
}