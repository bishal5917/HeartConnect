package com.example.heartconnect.features.presentation.screens.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heartconnect.components.ButtonComponent
import com.example.heartconnect.components.CustomText
import com.example.heartconnect.components.NormalButton
import com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel.StepEvent
import com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel.StepViewModel
import com.example.heartconnect.navigation.AllScreen
import com.example.heartconnect.ui.theme.VSizedBox1
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