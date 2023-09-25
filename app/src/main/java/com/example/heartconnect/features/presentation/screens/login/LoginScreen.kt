package com.example.heartconnect.features.presentation.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.components.*
import com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel.StepEvent
import com.example.heartconnect.navigation.AllScreen
import com.example.heartconnect.navigation.Navigator
import com.example.heartconnect.ui.theme.Secondary
import com.example.heartconnect.ui.theme.VSizedBox2
import com.example.heartconnect.utils.UnfocusKeyboard
import com.google.common.util.concurrent.Striped.lock

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {

    val loginState by loginViewModel.loginState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    when (loginState.status) {
        LoginState.Status.LOADING -> {
            CustomLoadingDialog(message = loginState.message)
        }
        LoginState.Status.SUCCESS -> {
            Navigator().navigateOffAll(
                navController, AllScreen.MainScreen.name, AllScreen.LoginScreen.name
            )
            CustomToast(message = loginState.message)
        }
        LoginState.Status.FAILED -> {
            CustomToast(message = loginState.message)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                CustomText(data = "Login", fontWeight = FontWeight.W600, fontSize = 16)
                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp)),
                    labelValue = "Email",
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginEvent.EmailChanged(it))
                    },
                    isEnabled = loginState.status != LoginState.Status.LOADING,
                    errorStatus = loginState.emailError
                )

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.message),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginEvent.PasswordChanged(it))
                    },
                    isEnabled = loginState.status != LoginState.Status.LOADING,
                    errorStatus = loginState.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    CustomText(data = "Forgot Password?",
                        fontWeight = FontWeight.W400,
                        fontSize = 12,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {})
                }

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        keyboardController?.hide()
                        loginViewModel.onEvent(LoginEvent.LoginUser)
                    },
                    isEnabled = loginState.emailError && loginState.passwordError,
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerText()

                Spacer(modifier = Modifier.height(20.dp))

                NormalButton(
                    buttonText = "Register", modifier = Modifier
                        .heightIn(50.dp)
                        .fillMaxWidth()
                ) {
                    Navigator().navigateTo(navController, AllScreen.RegisterScreen.name)
                }
            }
        }
    }
}
