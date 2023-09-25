package com.example.heartconnect.features.presentation.screens.message

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.components.*
import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.presentation.screens.chat.components.ConvoComponent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatEvent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatState
import com.example.heartconnect.features.presentation.screens.home.components.HomeCard
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeEvent
import com.example.heartconnect.features.presentation.screens.login.LoginEvent
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.features.presentation.screens.message.components.SingleMessage
import com.example.heartconnect.features.presentation.screens.message.viewmodel.MessageEvent
import com.example.heartconnect.features.presentation.screens.message.viewmodel.MessageState
import com.example.heartconnect.features.presentation.screens.message.viewmodel.MessageViewModel
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
        SendMessageComponent()
    }) {
        Surface(
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
                                    convoId = conversationId?:"",
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
@Preview(showBackground = true)
fun SendMessageComponent() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        CustomTextField(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            labelValue = "Type a Message ...",
            painterResource(id = R.drawable.message),
            onTextChanged = {},
            isEnabled = true,
            errorStatus = true,
        )
        CustomIconButton(
            contentDesc = "Send", childIcon = Icons.Default.Send, color = Primary,
            iconSize = 34
        ) {
            //
        }
    }
}
