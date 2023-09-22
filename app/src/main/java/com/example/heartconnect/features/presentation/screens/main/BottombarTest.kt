package com.example.heartconnect.features.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heartconnect.features.presentation.screens.chat.ChatScreen
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatEvent
import com.example.heartconnect.features.presentation.screens.chat.viewmodel.ChatViewModel
import com.example.heartconnect.features.presentation.screens.home.HomeScreen
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeEvent
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeState
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeViewModel
import com.example.heartconnect.features.presentation.screens.profile.ProfileScreen
import com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel.StepEvent
import com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel.StepState
import com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel.StepViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.Primary
import com.example.heartconnect.ui.theme.VSizedBox4

@Composable
fun BottomBarTest(
    navController: NavController,
    stepViewModel: StepViewModel = viewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    chatViewModel: ChatViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val stepState by stepViewModel.stepState.collectAsState()
    val userId by splashViewModel.userId.collectAsState()

    LaunchedEffect(key1 = "Main") {
        homeViewModel.onEvent(HomeEvent.GetFeed(userId))
        chatViewModel.onEvent(ChatEvent.GetChats(userId))
    }

    ConstraintLayout {
        val (body, bottomNavBar) = createRefs()

        Box(modifier = Modifier
            .constrainAs(body) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxSize()
        ) {

            //EVERYTHING TO SHOW HERE
            if (stepState.step == 0) {
                HomeScreen(navController = navController)
            }
            if (stepState.step == 1) {
                ChatScreen(navController = navController)
            }
            if (stepState.step == 2) {
                ProfileScreen(navController = navController)
            }
        }

        BottomNavigation(
            modifier = Modifier
                .background(color = Color.White)
                .constrainAs(bottomNavBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            BottomNavigationItem(selected = stepState.step == 0, onClick = {
                stepViewModel.onEvent(StepEvent.Change(0))
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint = if (stepState.step == 0) Primary else Color.Gray
                )
            }, label = { Text(text = "Home") })
            BottomNavigationItem(selected = stepState.step == 1, onClick = {
                stepViewModel.onEvent(StepEvent.Change(1))
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Message,
                    contentDescription = null,
                    tint = if (stepState.step == 1) Primary else Color.Gray
                )
            }, label = { Text(text = "Chats") })
            BottomNavigationItem(selected = stepState.step == 2, onClick = {
                stepViewModel.onEvent(StepEvent.Change(2))
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = if (stepState.step == 2) Primary else Color.Gray
                )
            }, label = { Text(text = "Profile") })
        }
    }
}