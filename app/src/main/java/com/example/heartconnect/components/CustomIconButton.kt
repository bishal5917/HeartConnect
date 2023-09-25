package com.example.heartconnect.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconButton(
    contentDesc: String,
    childIcon: ImageVector,
    color: Color = Color.Black,
    iconSize: Int = 30,
    onButtonClicked: () -> Unit
) {
    IconButton(onClick = { onButtonClicked() }) {
        Icon(
            imageVector = childIcon,
            contentDescription = contentDesc,
            tint = color,
            modifier = Modifier.size(iconSize.dp)
        )
    }
}