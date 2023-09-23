package com.example.heartconnect.features.presentation.screens.home

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heartconnect.components.CustomCircularProgressIndicator
import com.example.heartconnect.components.CustomErrorComponent
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.presentation.screens.home.components.HomeCard
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeEvent
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeState
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.VSizedBox1

@Composable
fun HomeScreen(
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
                    HomeCard(feedItem)
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