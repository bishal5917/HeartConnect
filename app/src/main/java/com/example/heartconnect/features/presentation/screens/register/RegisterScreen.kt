package com.example.heartconnect.features.presentation.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heartconnect.composables.CustomText
import com.example.heartconnect.composables.NormalButton
import com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel.StepEvent
import com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel.StepViewModel
import com.example.heartconnect.core.navigation.AllScreen
import com.example.heartconnect.ui.theme.VSizedBox2

@Composable
fun RegisterScreen(navController: NavController, stepViewModel: StepViewModel = viewModel()) {

    val stepState by stepViewModel.stepState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomText(data = "Register", fontSize = 20, fontWeight = FontWeight.W500)
            VSizedBox2()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NormalButton(buttonText = "Back") {
                    stepViewModel.onEvent(StepEvent.Decrement)
                }
                CustomText(
                    data = "Step ${stepState.step}", fontSize = 16, fontWeight = FontWeight.W400
                )
                NormalButton(buttonText = "Next") {
                    if (stepState.step == 2) {
                        navController.navigate(AllScreen.MainScreen.name) {
                            popUpTo(AllScreen.RegisterScreen.name) {
                                inclusive = true
                            }
                        }
                    } else {
                        stepViewModel.onEvent(StepEvent.Increment)
                    }
                }
            }
            VSizedBox2()
            if (stepState.step == 0) {

            }
            if (stepState.step == 1) {

            }
            if (stepState.step == 2) {

            }
        }
    }
}