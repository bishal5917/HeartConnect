package com.example.heartconnect.features.presentation.screens.feed.components

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.composables.*
import com.example.heartconnect.core.navigation.AllScreen
import com.example.heartconnect.core.navigation.Navigator
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel.CreateChatViewModel
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.get_chat_viewmodel.ChatViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.Primary
import com.example.heartconnect.ui.theme.VSizedBox0
import com.example.heartconnect.ui.theme.kNeutral100Color
import com.example.heartconnect.ui.theme.kNeutral300Color
import com.example.heartconnect.utils.ChatUtil

@Composable
fun FeedCard(navController: NavController, cardItem: FeedModel) {
    val createChatViewModel = hiltViewModel<CreateChatViewModel>()
    val chatViewModel = hiltViewModel<ChatViewModel>()
    val chatState by chatViewModel.chatState.collectAsState()

    val splashViewModel = hiltViewModel<SplashViewModel>()
    val userId by splashViewModel.userIdFlow.collectAsState()

    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 1.dp, color = kNeutral300Color, shape = RoundedCornerShape(12.dp))
            .clickable {
                //goto single feed screen
                Navigator().navigateTo(
                    navController,
                    "${AllScreen.SingleFeedScreen.name}/${cardItem.uid}"
                )
            },
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            CustomNetworkImage(
                imageUrl = cardItem.profileImage ?: "",
                modifier = Modifier.fillMaxSize(),
                parentmodifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            VSizedBox0()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomText(
                    data = "${cardItem.name} , ${cardItem.birthYear}",
                    fontWeight = FontWeight.W600,
                    fontSize = 16,
                    color = Color.Gray
                )
                CustomIconButton(
                    contentDesc = "Create", childIcon = if (!ChatUtil().isFriendAlready(
                            chatState.chats ?: emptyList(), cardItem.uid ?: ""
                        )
                    ) Icons.Default.FavoriteBorder else Icons.Default.Favorite, color = Primary
                ) {
                    //create chat api
//                    createChatViewModel.onEvent(
//                        CreateChatEvent.CreateChat(
//                            ChatRequestModel(userId = userId, friendId = cardItem.uid ?: "")
//                        )
//                    )
                }
            }

            VSizedBox0()
            LazyRow(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(cardItem.hobbies ?: listOf("")) { hobby ->
                    CustomText(
                        data = hobby,
                        fontWeight = FontWeight.W500, fontSize = 12, color = Color.Gray
                    )
                }
            }
        }
    }
}
