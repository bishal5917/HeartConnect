package com.example.heartconnect.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.heartconnect.ui.theme.Primary

@Composable
fun NormalButton(buttonText: String, onClicked: () -> Unit) {
    Button(
        modifier = Modifier
            .heightIn(40.dp),
        onClick = {
            onClicked()
        },
        contentPadding = PaddingValues(),
        border = BorderStroke(width = 1.dp, color = Primary),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        enabled = true
    ) {
        CustomText(data = buttonText, fontSize = 14, fontWeight = FontWeight.W400)
    }

}