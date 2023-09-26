package com.example.heartconnect.features.presentation.screens.chat.viewmodel.get_chat_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.domain.usecases.GetConversationsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val getConversationsUsecase: GetConversationsUsecase) :
    ViewModel() {

    private val _chatState = MutableStateFlow(ChatState.IDLE)
    val chatState: StateFlow<ChatState> = _chatState

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.GetChats -> {
                getChats(event.userId)
            }
        }
    }

    private fun getChats(id : String) = viewModelScope.launch {
        _chatState.value = _chatState.value.copy(
            status = ChatState.Status.LOADING, message = "Getting Chats , please wait ..."
        )
        try {
            val result = getConversationsUsecase.call(id)
            _chatState.value = _chatState.value.copy(
                status = ChatState.Status.SUCCESS, message = "Chats fetched", chats = result,
            )
        } catch (ex: Exception) {
            _chatState.value = _chatState.value.copy(
                status = ChatState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}

