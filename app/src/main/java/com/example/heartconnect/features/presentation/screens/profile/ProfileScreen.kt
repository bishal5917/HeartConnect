package com.example.heartconnect.features.presentation.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.components.ButtonComponent
import com.example.heartconnect.components.CustomCircularProgressIndicator
import com.example.heartconnect.components.CustomToast
import com.example.heartconnect.features.presentation.screens.login.LoginEvent
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.features.presentation.screens.login.LoginViewModel
import com.example.heartconnect.navigation.AllScreen

@Composable
fun ProfileScreen(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {

    val loginState by loginViewModel.loginState.collectAsState()

    when (loginState.status) {
        LoginState.Status.LOGOUTLOADING -> {
            CustomToast(message = loginState.message)
        }
        LoginState.Status.LOGOUTSUCCESS -> {
            CustomToast(message = loginState.message)
        }
        LoginState.Status.LOGOUTFAILED -> {
            CustomToast(message = loginState.message)
        }
    }

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Profile")
        if (loginState.status == LoginState.Status.LOGOUTLOADING) {
            CustomCircularProgressIndicator()
        } else {
            ButtonComponent(
                value = stringResource(id = R.string.logout),
                onButtonClicked = {
                    loginViewModel.onEvent(LoginEvent.LogoutUser)
                },
                isEnabled = true,
            )
        }
    }
}