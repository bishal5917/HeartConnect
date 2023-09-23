package com.example.heartconnect.features.presentation.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.components.CustomCircularProgressIndicator
import com.example.heartconnect.components.CustomErrorComponent
import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.presentation.screens.chat.components.ConvoComponent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatEvent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatState
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.VSizedBox2

@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    val chatState by chatViewModel.chatState.collectAsState()
    val userId = splashViewModel.userIdFlow.collectAsState()

    if (chatState.status != ChatState.Status.SUCCESS) {
        LaunchedEffect(key1 = "Chat") {
            chatViewModel.onEvent(ChatEvent.GetChats(userId = userId.value))
        }
    }

    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        if (chatState.status == ChatState.Status.LOADING) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CustomCircularProgressIndicator()
            }
        }
        if (chatState.status == ChatState.Status.SUCCESS && chatState.chats != null) {
            LazyColumn {
                items(chatState.chats ?: listOf(ConversationModel())) { chatItem ->
                    ConvoComponent(chatItem)
                    VSizedBox2()
                }
            }
        }
        if (chatState.status == ChatState.Status.FAILED) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CustomErrorComponent(message = chatState.message ?: "") {
                    chatViewModel.onEvent(ChatEvent.GetChats(userId = userId.value))
                }
            }
        }
    }
}