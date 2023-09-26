package com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.data.models.chat.ChatRequestModel
import com.example.heartconnect.features.domain.usecases.CreateChatUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateChatViewModel @Inject constructor(private val createChatUsecase: CreateChatUsecase) :
    ViewModel() {
    private val _createChatState = MutableStateFlow(CreateChatState.IDLE)
    val createChatState: StateFlow<CreateChatState> = _createChatState

    fun onEvent(event: CreateChatEvent) {
        when (event) {
            is CreateChatEvent.CreateChat -> {
                createChat(event.chatRequestModel)
            }
        }
    }

    private fun createChat(chatRequestModel: ChatRequestModel) = viewModelScope.launch {
        _createChatState.value = _createChatState.value.copy(
            status = CreateChatState.Status.LOADING, message = "Getting messages , please wait ..."
        )
        try {
            val result = createChatUsecase.call(chatRequestModel)
            _createChatState.value = _createChatState.value.copy(
                status = CreateChatState.Status.SUCCESS, message = result.message
            )
        } catch (ex: Exception) {
            _createChatState.value = _createChatState.value.copy(
                status = CreateChatState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}