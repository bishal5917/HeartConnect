package com.example.heartconnect.features.presentation.screens.message.viewmodel.send_message_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.domain.usecases.SendMessageUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendMessageViewModel @Inject constructor(private val sendMessageUsecase: SendMessageUsecase) :
    ViewModel() {
    private val _sendMessageState = MutableStateFlow(SendMessageState.IDLE)
    val sendMessageState: StateFlow<SendMessageState> = _sendMessageState

    fun onEvent(event: SendMessageEvent) {
        when (event) {
            is SendMessageEvent.MessageTyping -> {
                _sendMessageState.value = _sendMessageState.value.copy(
                    typedMessage = event.typedMessage
                )
            }
            is SendMessageEvent.SendMessage -> {
                if (_sendMessageState.value.typedMessage.isNotEmpty()) sendMessage(event.messageRequestModel)
            }
            is SendMessageEvent.Reset -> {
                _sendMessageState.value = _sendMessageState.value.copy(
                    typedMessage = ""
                )
            }
        }
    }

    private fun sendMessage(messageRequestModel: MessageRequestModel) = viewModelScope.launch {
        _sendMessageState.value = _sendMessageState.value.copy(
            status = SendMessageState.Status.LOADING, message = "Sending , please wait ..."
        )
        try {
            val result = sendMessageUsecase.call(
                MessageRequestModel(
                    message = _sendMessageState.value.typedMessage,
                    convoId = messageRequestModel.convoId,
                    userId = messageRequestModel.userId,
                    dateTime = "",
                )
            )
            _sendMessageState.value = _sendMessageState.value.copy(
                status = SendMessageState.Status.SUCCESS, message = "Message Sent"
            )
        } catch (ex: Exception) {
            _sendMessageState.value = _sendMessageState.value.copy(
                status = SendMessageState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}