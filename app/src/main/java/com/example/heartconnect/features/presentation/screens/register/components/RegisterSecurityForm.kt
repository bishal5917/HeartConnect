package com.example.heartconnect.features.presentation.screens.register.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.heartconnect.R
import com.example.heartconnect.composables.*
import com.example.heartconnect.core.navigation.AllScreen
import com.example.heartconnect.core.navigation.Navigator
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterEvent
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterState
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterViewModel
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox2
import com.example.heartconnect.ui.theme.kNeutral400Color
import com.example.heartconnect.ui.theme.kNeutral600Color
import com.example.heartconnect.utils.viewmodel.ImageEvent
import com.example.heartconnect.utils.viewmodel.ImageState
import com.example.heartconnect.utils.viewmodel.ImageViewModel

@Composable
fun RegisterSecurityForm() {
    val registerViewModel = hiltViewModel<RegisterViewModel>()
    val imageViewModel = viewModel<ImageViewModel>()

    val registerState by registerViewModel.registerState.collectAsState()
    val imageState by imageViewModel.imageState.collectAsState()

    when (imageState.status) {
        ImageState.Status.RegisterImageFailed -> {
            CustomToast(message = imageState.message)
        }
    }

    //for image picking
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
            onResult = {
                imageViewModel.onEvent(
                    ImageEvent.SelectRegisterImage(
                        context = context, imageUri = it
                    )
                )
            })

    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VSizedBox1()
        Box(
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            if (imageState.status == ImageState.Status.RegisterImageLoading) {
                CustomCircularProgressIndicator()
            } else if (imageState.status == ImageState.Status.RegisterImageSuccess && imageState.registerImage != null) {
                LocalBitImage(imageState.registerImage!!)
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person",
                    modifier = Modifier.size(50.dp),
                    tint = kNeutral600Color,
                )
            }
        }
        VSizedBox1()
        NormalButton(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            buttonText = if (imageState.status == ImageState.Status.RegisterImageSuccess && imageState.registerImage != null) "Change Profile Picture" else "Select Profile " + "Picture"
        ) {
            launcher.launch("image/*")
        }
        VSizedBox2()
        PasswordTextField(
            labelValue = stringResource(id = R.string.password),
            onTextSelected = {
                registerViewModel.onEvent(RegisterEvent.PasswordChanged(it))
            },
            passwordValue = registerState.password,
            isEnabled = true,
            errorStatus = registerState.passwordError
        )
    }
}