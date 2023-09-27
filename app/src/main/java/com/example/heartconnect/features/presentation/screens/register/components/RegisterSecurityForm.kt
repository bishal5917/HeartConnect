package com.example.heartconnect.features.presentation.screens.register.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.heartconnect.R
import com.example.heartconnect.composables.NormalButton
import com.example.heartconnect.composables.PasswordTextField
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterEvent
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterViewModel
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.utils.viewmodel.ImageEvent
import com.example.heartconnect.utils.viewmodel.ImageState
import com.example.heartconnect.utils.viewmodel.ImageViewModel

@Composable
fun RegisterSecurityForm() {
    val registerViewModel = hiltViewModel<RegisterViewModel>()
    val imageViewModel = viewModel<ImageViewModel>()

    val registerState by registerViewModel.registerState.collectAsState()
    val imageState by imageViewModel.imageState.collectAsState()

    //for image pick
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageViewModel.onEvent(
                ImageEvent.SelectRegisterImage(
                    context = context, imageUri = it!!
                )
            )
        })

    Column {
        PasswordTextField(
            labelValue = stringResource(id = R.string.password),
            onTextSelected = {
                registerViewModel.onEvent(RegisterEvent.PasswordChanged(it))
            },
            passwordValue = registerState.password,
            isEnabled = true,
            errorStatus = registerState.passwordError
        )
        VSizedBox1()
        if (imageState.status == ImageState.Status.RegisterImageSuccess && imageState.registerImage != null) {
            Image(
                bitmap = imageState.registerImage!!,
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
        VSizedBox1()
        NormalButton(buttonText = "Select Image") {

            launcher.launch("image/*")
        }
    }
}