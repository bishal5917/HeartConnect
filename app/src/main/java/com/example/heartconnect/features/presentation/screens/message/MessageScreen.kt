package com.example.heartconnect.features.presentation.screens.message

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.components.CustomCircularProgressIndicator
import com.example.heartconnect.components.CustomErrorComponent
import com.example.heartconnect.components.CustomNetworkImage
import com.example.heartconnect.components.CustomText
import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.presentation.screens.chat.components.ConvoComponent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatEvent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatState
import com.example.heartconnect.features.presentation.screens.home.components.HomeCard
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeEvent
import com.example.heartconnect.features.presentation.screens.message.components.SingleMessage
import com.example.heartconnect.features.presentation.screens.message.viewmodel.MessageEvent
import com.example.heartconnect.features.presentation.screens.message.viewmodel.MessageState
import com.example.heartconnect.features.presentation.screens.message.viewmodel.MessageViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.HSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox2

@Composable
fun MessageScreen(
    navController: NavController,
) {
    val messageViewModel = hiltViewModel<MessageViewModel>()
    val splashViewModel = hiltViewModel<SplashViewModel>()
    val messageState by messageViewModel.messageState.collectAsState()
    val userId by splashViewModel.userIdFlow.collectAsState()

    LaunchedEffect(key1 = true) {
        messageViewModel.onEvent(
            MessageEvent.GetMessages(
                MessageRequestModel(
                    userId = userId,
                    convoId = "2g5VBg2n93IdHoFNBFPv",
                )
            )
        )
    }

    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        if (messageState.status == MessageState.Status.LOADING) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CustomCircularProgressIndicator()
            }
        }
        if (messageState.status == MessageState.Status.SUCCESS) {
            LazyColumn {
                items(messageState.messages ?: listOf(MessageModel())) { msgItem ->
                    SingleMessage(messageModel = msgItem)
                    VSizedBox1()
                }
            }
        }
        if (messageState.status == MessageState.Status.FAILED) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CustomErrorComponent(message = messageState.message ?: "") {
                    messageViewModel.onEvent(
                        MessageEvent.GetMessages(
                            MessageRequestModel(
                                userId = userId,
                                convoId = "2g5VBg2n93IdHoFNBFPv",
                            )
                        )
                    )
                }
            }
        }
    }
}