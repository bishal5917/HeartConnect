package com.example.heartconnect.features.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.composables.LocalImage
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashEvent
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import com.example.heartconnect.R
import com.example.heartconnect.composables.CustomCircularProgressIndicator
import com.example.heartconnect.composables.NormalButton
import com.example.heartconnect.core.navigation.AllScreen
import com.example.heartconnect.core.navigation.Navigator
import com.example.heartconnect.ui.theme.SplashBgColor
import com.example.heartconnect.ui.theme.VSizedBox4

@Composable
fun SplashScreen(navController: NavController, splashViewModel: SplashViewModel = hiltViewModel()) {

    val scale = remember {
        Animatable(0f)
    }

    val userIdFlow by splashViewModel.userIdFlow.collectAsState()

    LaunchedEffect(key1 = true) {
        splashViewModel.onEvent(SplashEvent.CheckStatus)
        scale.animateTo(
            targetValue = 0.9f, animationSpec = tween(durationMillis = 800, easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            })
        )
        delay(1500L)
        if (userIdFlow.isEmpty()) {
            Navigator().navigateOffAll(
                navController, AllScreen.LoginScreen.name, AllScreen.SplashScreen.name
            )
        } else {
            Navigator().navigateOffAll(
                navController, AllScreen.MainScreen.name, AllScreen.SplashScreen.name
            )
        }
    }

    Box(
        modifier = Modifier.background(color = SplashBgColor), contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            VSizedBox4()
            LocalImage(image = R.drawable.logo)
            CustomCircularProgressIndicator()
        }
    }
}