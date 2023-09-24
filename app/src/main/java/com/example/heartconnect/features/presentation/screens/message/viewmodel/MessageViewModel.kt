package com.example.heartconnect.features.presentation.screens.message.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.domain.usecases.GetMessagesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(private val getMessagesUsecase: GetMessagesUsecase) :
    ViewModel() {
    private val _messageState = MutableStateFlow(MessageState.IDLE)
    val messageState: StateFlow<MessageState> = _messageState

    fun onEvent(event: MessageEvent) {
        when (event) {
            is MessageEvent.GetMessages -> {
                getMessages(event.messageRequestModel)
            }
        }
    }

    private fun getMessages(messageRequestModel: MessageRequestModel) = viewModelScope.launch {
        _messageState.value = _messageState.value.copy(
            status = MessageState.Status.LOADING, message = "Getting messages , please wait ..."
        )
        try {
            val result = getMessagesUsecase.call(messageRequestModel)
            _messageState.value = _messageState.value.copy(
                status = MessageState.Status.SUCCESS, message = "Feed fetched", messages = result,
            )
        } catch (ex: Exception) {
            _messageState.value = _messageState.value.copy(
                status = MessageState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}