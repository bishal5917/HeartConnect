package com.example.heartconnect.features.presentation.screens.chat.viewmodel

sealed class ChatEvent {
    object GetChats : ChatEvent()
}