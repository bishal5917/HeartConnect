package com.example.heartconnect.features.presentation.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.heartconnect.components.ButtonComponent
import com.example.heartconnect.components.CustomText
import com.example.heartconnect.components.NormalButton
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox2

@Composable
fun RegisterScreen(navController: NavController) {
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
                NormalButton(buttonText = "Back") {}
                CustomText(data = "Step 1", fontSize = 16, fontWeight = FontWeight.W400)
                NormalButton(buttonText = "Next") {}
            }
            VSizedBox2()
        }

    }
}