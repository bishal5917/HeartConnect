package com.example.heartconnect.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    data: String,
    color: Color = Color.Black,
    fontSize: Int = 14,
    fontWeight: FontWeight = FontWeight.W400,
    maxLine: Int = 1,
    textDecoration: TextDecoration = TextDecoration.None
) {
    Text(
        text = data,
        fontSize = fontSize.sp,
        color = color,
        textAlign = TextAlign.Center,
        fontWeight = fontWeight,
        modifier = modifier,
        maxLines = maxLine,
        textDecoration = textDecoration,
    )
}