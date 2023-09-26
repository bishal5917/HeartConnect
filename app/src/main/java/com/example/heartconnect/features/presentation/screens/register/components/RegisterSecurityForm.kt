package com.example.heartconnect.features.presentation.screens.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartconnect.R
import com.example.heartconnect.composables.PasswordTextField
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterEvent
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterViewModel
import com.example.heartconnect.ui.theme.VSizedBox1

@Composable
fun RegisterSecurityForm() {
    val registerViewModel = hiltViewModel<RegisterViewModel>()
    val registerState by registerViewModel.registerState.collectAsState()
    Column {
        PasswordTextField(
            labelValue = stringResource(id = R.string.password),
            onTextSelected = {
                registerViewModel.onEvent(RegisterEvent.PasswordChanged(it))
            },
            isEnabled = true,
            errorStatus = registerState.passwordError
        )
        VSizedBox1()
    }
}