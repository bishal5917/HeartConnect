package com.example.heartconnect.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun LocalBitImage(image: ImageBitmap) {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        bitmap = image,
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
}