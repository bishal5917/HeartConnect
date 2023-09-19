package com.example.heartconnect.features.presentation.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.components.*
import com.example.heartconnect.features.presentation.screens.login.LoginEvent
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.features.presentation.screens.login.LoginViewModel
import com.example.heartconnect.navigation.AllScreen
import com.example.heartconnect.ui.theme.VSizedBox2
import com.example.heartconnect.ui.theme.VSizedBox4

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            VSizedBox2()
            CustomNetworkImage(
                imageUrl = "https://i.pinimg" + ".com/236x/45/2b/6e/452b6e38b8f1d183941b31d4ee5959c3.jpg",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                parentmodifier = Modifier.size(100.dp)
            )
            VSizedBox2()
                CustomText(
                    data = "Tim Berg , 1990",
                    fontWeight = FontWeight.W400,
                    fontSize = 24
                )
            VSizedBox2()
            CustomListTile(
                title = "Change Picture", leadingIcon = Icons.Default.Person,
                onClick = {
                    //onclicked function
                },
            )
            CustomListTile(
                title = "Change Password", leadingIcon = Icons.Default.LockClock,
                onClick = {
                    //onclicked function
                },
            )
            CustomListTile(
                title = "Add Post", leadingIcon = Icons.Default.AddAPhoto,
                onClick = {
                    //onclicked function
                },
            )
            CustomListTile(
                title = "Logout", leadingIcon = Icons.Default.Logout,
                onClick = {
                    loginViewModel.onEvent(LoginEvent.LogoutUser)
                },
            )
        }
    }
}