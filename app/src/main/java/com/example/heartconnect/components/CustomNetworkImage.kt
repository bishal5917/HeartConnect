package com.example.heartconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.example.heartconnect.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CustomNetworkImage(
    modifier: Modifier = Modifier, imageUrl: String, parentmodifier:
    Modifier
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        val painter = rememberImagePainter(data = imageUrl, builder = {
            placeholder(R.drawable.placeholder)
            error(R.drawable.error)
            crossfade(1000)
            transformations(
                RoundedCornersTransformation(4f)
            )
        })
        // Circular progress indicator displayed while loading
        if (painter.state is ImagePainter.State.Loading) {
            CustomCircularProgressIndicator()
        } else {
            Image(
                painter = painter, contentDescription = "Network Image",
                contentScale =
                ContentScale.Crop,
                modifier = modifier,
            )
        }

    }

}
