package com.example.heartconnect.features.presentation.screens.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.composables.CustomCircularProgressIndicator
import com.example.heartconnect.composables.CustomErrorComponent
import com.example.heartconnect.composables.CustomLoadingDialog
import com.example.heartconnect.composables.CustomToast
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel.CreateChatEvent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel.CreateChatState
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.create_chat_viewmodel.CreateChatViewModel
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.get_chat_viewmodel.ChatEvent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.get_chat_viewmodel.ChatViewModel
import com.example.heartconnect.features.presentation.screens.feed.components.FeedCard
import com.example.heartconnect.features.presentation.screens.feed.viewmodel.HomeEvent
import com.example.heartconnect.features.presentation.screens.feed.viewmodel.HomeState
import com.example.heartconnect.features.presentation.screens.feed.viewmodel.HomeViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.VSizedBox1

@Composable
fun FeedScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val homeState by homeViewModel.homeState.collectAsState()
    val userId by splashViewModel.userIdFlow.collectAsState()

    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        if (homeState.status == HomeState.Status.LOADING) {
            CustomCircularProgressIndicator()
        }
        if (homeState.status == HomeState.Status.SUCCESS && homeState.users != null) {
            LazyColumn {
                items(homeState.users ?: listOf(FeedModel())) { feedItem ->
                    FeedCard(navController,feedItem)
                    VSizedBox1()
                }
            }
        }
        if (homeState.status == HomeState.Status.FAILED) {
            CustomErrorComponent(message = homeState.message ?: "") {
                homeViewModel.onEvent(HomeEvent.GetFeed(userId))
            }
        }
    }
}