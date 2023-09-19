package com.example.heartconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.heartconnect.R

@Composable
fun LocalImage(modifier: Modifier = Modifier, image: Int?) {
    Image(
        painter = painterResource(id = image ?: R.drawable.logo),
        contentDescription = null,
        modifier = Modifier,
        contentScale = ContentScale.Fit,
    )
}