package com.example.heartconnect.features.presentation.screens.feed.components.single_feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry

import androidx.navigation.NavController
import com.example.heartconnect.composables.CustomAppbar
import com.example.heartconnect.composables.CustomCircularProgressIndicator
import com.example.heartconnect.composables.CustomErrorComponent
import com.example.heartconnect.composables.CustomIconButton
import com.example.heartconnect.composables.CustomLoadingDialog
import com.example.heartconnect.composables.CustomNetworkImage
import com.example.heartconnect.composables.CustomText
import com.example.heartconnect.composables.CustomToast
import com.example.heartconnect.features.data.models.chat.ChatRequestModel
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel.CreateChatEvent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel.CreateChatState
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel.CreateChatViewModel
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.get_chat_viewmodel.ChatEvent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.get_chat_viewmodel.ChatViewModel
import com.example.heartconnect.features.presentation.screens.feed.components.single_feed.viewmodel.SingleFeedEvent
import com.example.heartconnect.features.presentation.screens.feed.components.single_feed.viewmodel.SingleFeedState
import com.example.heartconnect.features.presentation.screens.feed.components.single_feed.viewmodel.SingleFeedViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.model.CommonRequestModel
import com.example.heartconnect.ui.theme.HSizedBox2
import com.example.heartconnect.ui.theme.Primary
import com.example.heartconnect.ui.theme.Secondary
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox2
import com.example.heartconnect.ui.theme.kNeutral500Color
import com.example.heartconnect.ui.theme.kNeutral600Color
import com.example.heartconnect.ui.theme.kNeutral800Color

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleFeedScreen(
    navController: NavController, navBackStackEntry: NavBackStackEntry,
) {
    val feedId = navBackStackEntry.arguments?.getString("id")
    val splashViewModel = hiltViewModel<SplashViewModel>()
    val createChatViewModel = hiltViewModel<CreateChatViewModel>()
    val singleFeedViewModel = hiltViewModel<SingleFeedViewModel>()

    val userId by splashViewModel.userIdFlow.collectAsState()
    val createChatState by createChatViewModel.createChatState.collectAsState()
    val singleFeedState by singleFeedViewModel.singleFeedState.collectAsState()

    when (createChatState.status) {
        CreateChatState.Status.LOADING -> {
            CustomLoadingDialog(message = createChatState.message)
        }

        CreateChatState.Status.SUCCESS -> {
            hiltViewModel<ChatViewModel>().onEvent(ChatEvent.GetChats(userId))
            CustomToast(message = createChatState.message)
            createChatViewModel.onEvent(CreateChatEvent.Reset)
        }

        CreateChatState.Status.FAILED -> {
            CustomToast(message = createChatState.message)
        }

        else -> {}
    }

    LaunchedEffect(key1 = true) {
        singleFeedViewModel.onEvent(SingleFeedEvent.GetSingleFeed(CommonRequestModel(id = feedId)))
    }
    if (singleFeedState.status == SingleFeedState.Status.LOADING) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CustomCircularProgressIndicator()
        }
    }
    if (singleFeedState.status == SingleFeedState.Status.FAILED) {
        CustomErrorComponent(message = singleFeedState.message ?: "") {
            singleFeedViewModel.onEvent(SingleFeedEvent.GetSingleFeed(CommonRequestModel(id = feedId)))
        }
    }
    if (singleFeedState.status == SingleFeedState.Status.SUCCESS) {
        Scaffold(
            topBar = {
                CustomAppbar(navController, title = "", actionButtonClicked = {})
            },
            floatingActionButton = {
                CustomIconButton(
                    contentDesc = "Create",
                    childIcon = Icons.Default.FavoriteBorder,
                    color = Primary,
                    iconSize = 25,
                ) {
                    //create chat api
                    createChatViewModel.onEvent(
                        CreateChatEvent.CreateChat(
                            ChatRequestModel(userId = userId, friendId = feedId ?: "")
                        )
                    )
                }
            },
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                VSizedBox1()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    CustomNetworkImage(
                        imageUrl = singleFeedState.feedData?.profileImage ?: "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        parentmodifier = Modifier.size(100.dp)
                    )
                    HSizedBox2()
                    Column {
                        CustomText(
                            data = "${singleFeedState.feedData?.name} , ${
                                singleFeedState
                                    .feedData?.birthYear
                            }", fontWeight = FontWeight.W600,
                            fontSize = 16,
                            color = Color.Gray
                        )
                        VSizedBox1()
                        CustomText(
                            data = singleFeedState.feedData?.phone ?: "",
                            fontWeight = FontWeight
                                .W400,
                            fontSize = 14,
                            color = kNeutral500Color,
                        )
                        CustomText(
                            data = singleFeedState.feedData?.email ?: "",
                            fontWeight = FontWeight.W400,
                            fontSize = 14,
                            color = kNeutral500Color,
                        )
                    }
                }
                //Hobbies (Map those hobbies)
                VSizedBox2()
                LazyColumn {
                    items(singleFeedState.feedData?.hobbies ?: emptyList()) { hby ->
                        SingleHobby(hobby = hby)
                    }
                }
                VSizedBox2()
                //Show uploaded images grid
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(), cells = GridCells.Fixed(2)
                ) {
                    items(singleFeedState.feedData?.pics ?: emptyList()) { pic ->
                        CustomNetworkImage(
                            imageUrl = pic,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(2.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            parentmodifier = Modifier.size(200.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SingleHobby(hobby: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Secondary, shape = RoundedCornerShape(6.dp))
            .fillMaxWidth()
    ) {
        CustomText(
            modifier = Modifier.padding(6.dp),
            data = hobby,
            fontWeight = FontWeight.W400,
            fontSize = 16,
            maxLine = 5,
            color = kNeutral600Color
        )
    }
}