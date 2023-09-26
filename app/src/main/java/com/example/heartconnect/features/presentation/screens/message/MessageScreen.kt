package com.example.heartconnect.features.presentation.screens.message

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.composables.*
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.presentation.screens.message.components.SingleMessage
import com.example.heartconnect.features.presentation.screens.message.viewmodel.get_message_viewmodel.MessageEvent
import com.example.heartconnect.features.presentation.screens.message.viewmodel.get_message_viewmodel.MessageState
import com.example.heartconnect.features.presentation.screens.message.viewmodel.get_message_viewmodel.MessageViewModel
import com.example.heartconnect.features.presentation.screens.message.viewmodel.send_message_viewmodel.SendMessageEvent
import com.example.heartconnect.features.presentation.screens.message.viewmodel.send_message_viewmodel.SendMessageState
import com.example.heartconnect.features.presentation.screens.message.viewmodel.send_message_viewmodel.SendMessageViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.*

@Composable
fun MessageScreen(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
) {
    val conversationId = navBackStackEntry.arguments?.getString("id")
    val friendName = navBackStackEntry.arguments?.getString("name")

    val messageViewModel = hiltViewModel<MessageViewModel>()
    val splashViewModel = hiltViewModel<SplashViewModel>()
    val messageState by messageViewModel.messageState.collectAsState()
    val userId by splashViewModel.userIdFlow.collectAsState()

    //state for lazy list scroll controlling
    val lazyListState = rememberLazyListState()

    when (messageState.status) {
        MessageState.Status.SUCCESS -> {
            LaunchedEffect(key1 = true) {
                lazyListState.animateScrollToItem(Int.MAX_VALUE)
            }
        }
    }

    LaunchedEffect(key1 = true) {
        messageViewModel.onEvent(
            MessageEvent.GetMessages(
                MessageRequestModel(
                    userId = userId,
                    convoId = conversationId ?: "",
                )
            )
        )
    }

    Scaffold(topBar = {
        CustomAppbar(navController, title = friendName ?: "", actionButtonClicked = {})
    }, bottomBar = {
        SendMessageComponent(conversationId ?: "", lazyListState)
    }) {
        Box(
            modifier = Modifier
                .padding(bottom = 50.dp)
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
                LazyColumn(modifier = Modifier.padding(10.dp), state = lazyListState) {
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
                                    convoId = conversationId ?: "",
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SendMessageComponent(conversationId: String, lazyListState: LazyListState) {
    val sendMessageViewModel = hiltViewModel<SendMessageViewModel>()
    val splashViewModel = hiltViewModel<SplashViewModel>()

    val userId by splashViewModel.userIdFlow.collectAsState()
    val sendMessageState by sendMessageViewModel.sendMessageState.collectAsState()

    when (sendMessageState.status) {
        SendMessageState.Status.SUCCESS -> {
            //reset the fields
            sendMessageViewModel.onEvent(SendMessageEvent.Reset)
            LaunchedEffect(key1 = true) {
                lazyListState.animateScrollToItem(Int.MAX_VALUE)
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        CustomTextField(
            modifier = Modifier.clip(RoundedCornerShape(6.dp)),
            labelValue = "Type a Message ...",
            painterResource(id = R.drawable.message),
            onTextChanged = {
                sendMessageViewModel.onEvent(SendMessageEvent.MessageTyping(it))
            },
            isEnabled = true,
            errorStatus = true,
        )
        if (sendMessageState.status == SendMessageState.Status.LOADING) {
            CustomCircularProgressIndicator()
        } else {
            CustomIconButton(
                contentDesc = "Send", childIcon = Icons.Default.Send, color = Primary, iconSize = 34
            ) {
                sendMessageViewModel.onEvent(
                    SendMessageEvent.SendMessage(
                        MessageRequestModel(
                            userId = userId,
                            convoId = conversationId,
                        )
                    )
                )
            }
        }
    }
}
