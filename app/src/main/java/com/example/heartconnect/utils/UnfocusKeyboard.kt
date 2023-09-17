package com.example.heartconnect.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UnfocusKeyboard() {
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()

}

