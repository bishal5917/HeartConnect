package com.example.heartconnect.features.presentation.screens.profile.components.change_password

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.composables.ButtonComponent
import com.example.heartconnect.composables.CustomAppbar
import com.example.heartconnect.composables.PasswordTextField
import com.example.heartconnect.features.presentation.screens.login.LoginEvent
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.ui.theme.VSizedBox0
import com.example.heartconnect.ui.theme.VSizedBox2

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChangePasswordScreen(navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(topBar = {
        CustomAppbar(
            navController,
            title = stringResource(id = R.string.change_password),
            actionButtonClicked = {})
    }) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Column {
                VSizedBox0()
                PasswordTextField(
                    labelValue = stringResource(id = R.string.password), onTextSelected = {
//                    loginViewModel.onEvent(LoginEvent.PasswordChanged(it))
                    }, passwordValue = "", isEnabled = true, errorStatus = false
                )
                VSizedBox2()
                ButtonComponent(
                    value = stringResource(id = R.string.submit),
                    onButtonClicked = {
                        keyboardController?.hide()
//                        loginViewModel.onEvent(LoginEvent.LoginUser)
                    },
                    isEnabled = true,
                )
            }
        }
    }
}