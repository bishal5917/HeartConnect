package com.example.heartconnect.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.heartconnect.ui.theme.VSizedBox0

@Composable
fun CustomErrorComponent(
    modifier: Modifier = Modifier, message: String = "Error", onRetry: ()
    -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CustomText(data = "Information", fontWeight = FontWeight.W300, fontSize = 18)
        CustomText(
            data = message, fontWeight = FontWeight.W400, fontSize = 14, maxLine = 6,
            color = Color.Red,
        )
        VSizedBox0()
        CustomText(
            data = "Retry", fontWeight = FontWeight.W400, fontSize = 14,
            modifier = Modifier
                .clickable {
                    onRetry()
                },
            textDecoration = TextDecoration.Underline,
        )
    }
}