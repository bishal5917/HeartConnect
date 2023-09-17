package com.example.heartconnect.features.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heartconnect.components.CustomToast
import com.example.heartconnect.features.presentation.screens.login.LoginEvent
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashEvent
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashState
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.navigation.AllScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, splashViewModel: SplashViewModel = hiltViewModel()) {

    val scale = remember {
        Animatable(0f)
    }
    val splashState by splashViewModel.splashState.collectAsState()
    val user = splashViewModel.userId.collectAsState()


    LaunchedEffect(key1 = true) {
        splashViewModel.onEvent(SplashEvent.CheckStatus)
        scale.animateTo(
            targetValue = 0.9f, animationSpec = tween(durationMillis = 800, easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            })
        )
        delay(2000L)

//        when (splashState.status) {
//            SplashState.SplashStatus.LOGGEDIN -> {
//                navController.navigate(AllScreen.MainScreen.name)
//            }
//            SplashState.SplashStatus.LOGGEDOUT -> {
//                navController.navigate(AllScreen.LoginScreen.name)
//            }
//        }

        if (user.value.isEmpty()) {
            navController.navigate(AllScreen.LoginScreen.name)
        } else {
            navController.navigate(AllScreen.MainScreen.name)
        }

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Heart Connect",
            style = MaterialTheme.typography.h5,
            color = Color.Red.copy(alpha = 0.5f)
        )
    }

}