package com.example.heartconnect.features.presentation.screens.profile.components.change_picture

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.composables.ButtonComponent
import com.example.heartconnect.composables.CustomAppbar
import com.example.heartconnect.composables.CustomCircularProgressIndicator
import com.example.heartconnect.composables.CustomLoadingDialog
import com.example.heartconnect.composables.CustomToast
import com.example.heartconnect.composables.LocalBitImage
import com.example.heartconnect.composables.NormalButton
import com.example.heartconnect.core.navigation.AllScreen
import com.example.heartconnect.core.navigation.Navigator
import com.example.heartconnect.features.presentation.screens.login.LoginState
import com.example.heartconnect.features.presentation.screens.login.LoginViewModel
import com.example.heartconnect.features.presentation.screens.profile.components.change_picture.viewmodel.ChangePictureEvent
import com.example.heartconnect.features.presentation.screens.profile.components.change_picture.viewmodel.ChangePictureState
import com.example.heartconnect.features.presentation.screens.profile.components.change_picture.viewmodel.ChangePictureViewModel
import com.example.heartconnect.features.presentation.screens.profile.viewmodel.ProfileEvent
import com.example.heartconnect.features.presentation.screens.profile.viewmodel.ProfileViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.model.CommonRequestModel
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox2
import com.example.heartconnect.ui.theme.kNeutral600Color
import com.example.heartconnect.utils.viewmodel.ImageEvent
import com.example.heartconnect.utils.viewmodel.ImageState
import com.example.heartconnect.utils.viewmodel.ImageViewModel

@Composable
fun ChangePictureScreen(
    navController: NavController, profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    //viewmodels
    val imageViewModel = viewModel<ImageViewModel>()
    val changePictureViewModel = hiltViewModel<ChangePictureViewModel>()
    val splashViewModel = hiltViewModel<SplashViewModel>()

    //flow collection
    val imageState by imageViewModel.imageState.collectAsState()
    val changePictureState by changePictureViewModel.changePictureState.collectAsState()
    val uId by splashViewModel.userIdFlow.collectAsState()

    when (changePictureState.status) {
        ChangePictureState.Status.LOADING -> {
            CustomLoadingDialog(message = changePictureState.message)
        }

        ChangePictureState.Status.SUCCESS -> {
            Navigator().back(navController)
            CustomToast(message = changePictureState.message)
            profileViewModel.onEvent(ProfileEvent.GetProfile(uId))
        }

        ChangePictureState.Status.FAILED -> {
            CustomToast(message = changePictureState.message)
        }

        else -> {}
    }
    Scaffold(topBar = {
        CustomAppbar(
            navController,
            title = stringResource(id = R.string.change_picture),
            actionButtonClicked = {})
    }) {
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)
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
                    .fillMaxWidth()
                    .height(40.dp),
                buttonText = if (imageState.status == ImageState.Status.RegisterImageSuccess &&
                    imageState.registerImage != null
                ) "Change Image" else "Choose Image"
            ) {
                launcher.launch("image/*")
            }
            VSizedBox2()
            ButtonComponent(
                value = stringResource(id = R.string.submit),
                onButtonClicked = {
                    changePictureViewModel.onEvent(
                        ChangePictureEvent.ChangePicture
                            (CommonRequestModel(id = uId, image = imageState.registerImageUri))
                    )
                },
                isEnabled = imageState.status == ImageState.Status.RegisterImageSuccess,
            )
        }
    }
}