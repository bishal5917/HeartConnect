package com.example.heartconnect.features.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.components.CustomCircularProgressIndicator
import com.example.heartconnect.components.CustomToast
import com.example.heartconnect.features.data.models.HomeUser
import com.example.heartconnect.features.presentation.screens.home.components.HomeCard
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeEvent
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeState
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeViewModel
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.navigation.AllScreen
import com.example.heartconnect.ui.theme.VSizedBox0
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox4

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val homeState by homeViewModel.homeState.collectAsState()

    if (homeState.status !=HomeState.Status.SUCCESS){
        LaunchedEffect(key1 = true) {
            homeViewModel.onEvent(HomeEvent.GetFeed)
        }
    }

    Box(
        modifier = Modifier.padding(10.dp).fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (homeState.status == HomeState.Status.LOADING) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomCircularProgressIndicator()
            }
        }
        if (homeState.status == HomeState.Status.SUCCESS && homeState.users != null) {
            LazyColumn {
                items(homeState.users ?: listOf(HomeUser())) { feedItem ->
                    HomeCard(feedItem)
                    VSizedBox1()
                }
            }
        }
    }
}