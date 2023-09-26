package com.example.heartconnect.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
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
fun NormalButton(
    modifier: Modifier = Modifier,
    buttonText: String, isBorder: Boolean = true,
    titleFontSize: Int = 14, titleFontWeight: FontWeight = FontWeight.W400, borderColor: Color =
        Primary,
    onClicked: ()
    -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClicked()
        },
        contentPadding = PaddingValues(),
        border = if (isBorder) BorderStroke(width = 1.dp, color = borderColor) else null,
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(50.dp),
        enabled = true,
    ) {
        CustomText(data = buttonText, fontSize = titleFontSize, fontWeight = titleFontWeight)
    }

}