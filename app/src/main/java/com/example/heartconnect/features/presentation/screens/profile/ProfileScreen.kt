package com.example.heartconnect.features.presentation.screens.profile

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heartconnect.composables.*
import com.example.heartconnect.features.presentation.screens.login.LoginEvent
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.features.presentation.screens.login.LoginViewModel
import com.example.heartconnect.core.navigation.AllScreen
import com.example.heartconnect.core.navigation.Navigator
import com.example.heartconnect.features.presentation.screens.profile.viewmodel.ProfileEvent
import com.example.heartconnect.features.presentation.screens.profile.viewmodel.ProfileState
import com.example.heartconnect.features.presentation.screens.profile.viewmodel.ProfileViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.ui.theme.VSizedBox2

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val loginState by loginViewModel.loginState.collectAsState()
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val profileState by profileViewModel.profileState.collectAsState()
    val splashViewModel = hiltViewModel<SplashViewModel>()
    val userId by splashViewModel.userIdFlow.collectAsState()

    when (loginState.status) {
        LoginState.Status.LOGOUTLOADING -> {
            CustomLoadingDialog(message = loginState.message)
        }
        LoginState.Status.LOGOUTSUCCESS -> {
            Navigator().navigateOffAll(
                navController, AllScreen.LoginScreen.name, AllScreen.MainScreen.name
            )
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
        if (profileState.status == ProfileState.Status.LOADING) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CustomCircularProgressIndicator()
            }
        }
        if (profileState.status == ProfileState.Status.SUCCESS && profileState.user != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                VSizedBox2()
                CustomNetworkImage(
                    imageUrl = profileState.user!!.image ?: "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    parentmodifier = Modifier.size(100.dp)
                )
                VSizedBox2()
                CustomText(
                    data = "${profileState.user!!.name} , ${profileState.user!!.birthYear}",
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
        if (profileState.status == ProfileState.Status.FAILED) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CustomErrorComponent(message = profileState.message ?: "", onRetry = {
                    profileViewModel.onEvent(
                        ProfileEvent.GetProfile(
                            userId
                        )
                    )
                })
            }
        }
    }
}