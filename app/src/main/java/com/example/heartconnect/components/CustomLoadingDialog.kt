package com.example.heartconnect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.example.heartconnect.ui.theme.VSizedBox1

@Composable
fun CustomLoadingDialog(message: String?) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomText(
                data = message ?: "Requesting", fontSize = 12, fontWeight = FontWeight
                    .W400, color = Color.LightGray
            )
            VSizedBox1()
            CustomCircularProgressIndicator()
        }
    }
}