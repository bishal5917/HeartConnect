package com.example.heartconnect.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.heartconnect.ui.theme.Primary

@Composable
fun CustomCircularProgressIndicator() {
    CircularProgressIndicator(
        strokeWidth = 2.dp,
        color= Primary
    )
}