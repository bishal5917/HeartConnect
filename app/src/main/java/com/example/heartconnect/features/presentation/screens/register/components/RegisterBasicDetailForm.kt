package com.example.heartconnect.features.presentation.screens.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartconnect.R
import com.example.heartconnect.composables.CustomTextField
import com.example.heartconnect.features.presentation.screens.login.LoginEvent
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.features.presentation.screens.login.LoginViewModel
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterEvent
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterViewModel
import com.example.heartconnect.ui.theme.VSizedBox1

@Composable
fun RegisterBasicDetailForm() {
    val registerViewModel = hiltViewModel<RegisterViewModel>()
    val registerState by registerViewModel.registerState.collectAsState()

    Column {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp)),
            labelValue = "Name",
            leadingIcon = Icons.Default.Person,
            onTextChanged = {
                registerViewModel.onEvent(RegisterEvent.NameChanged(it))
            },
            textValue = registerState.name,
            isEnabled = true,
            errorStatus = registerState.nameError,
        )
        VSizedBox1()
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp)),
            labelValue = "Email",
            leadingIcon = Icons.Default.MailOutline,
            onTextChanged = {
                registerViewModel.onEvent(RegisterEvent.EmailChanged(it))
            },
            textValue = registerState.email,
            isEnabled = true,
            errorStatus = registerState.emailError,
        )
        VSizedBox1()
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp)),
            labelValue = "Phone",
            leadingIcon = Icons.Default.Phone,
            onTextChanged = {
                registerViewModel.onEvent(RegisterEvent.PhoneChanged(it))
            },
            textValue = registerState.phone,
            isEnabled = true,
            errorStatus = registerState.phoneError,
        )
        VSizedBox1()
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp)),
            labelValue = "Gender",
            leadingIcon = Icons.Default.Male,
            onTextChanged = {
                registerViewModel.onEvent(RegisterEvent.GenderChanged(it))
            },
            textValue = registerState.gender,
            isEnabled = true,
            errorStatus = registerState.genderError,
        )
        VSizedBox1()
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp)),
            labelValue = "BirthYear",
            leadingIcon = Icons.Default.Info,
            onTextChanged = {
                registerViewModel.onEvent(RegisterEvent.BirthYearChanged(it))
            },
            textValue = registerState.birthYear,
            isEnabled = true,
            errorStatus = registerState.birthYearError,
        )
        VSizedBox1()
    }
}