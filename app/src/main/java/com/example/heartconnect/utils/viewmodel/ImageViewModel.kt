package com.example.heartconnect.utils.viewmodel

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ImageViewModel : ViewModel() {

    private val _imageState = MutableStateFlow(ImageState.IDLE)
    val imageState: StateFlow<ImageState> = _imageState

    fun onEvent(event: ImageEvent) {
        when (event) {
            is ImageEvent.SelectRegisterImage -> {
                selectImage(event.imageUri, event.context)
            }
        }
    }

    private fun selectImage(imageUri: Uri, context: Context) {
        _imageState.value = _imageState.value.copy(
            status = ImageState.Status.RegisterImageLoading, message = "Loading register image ..."
        )
        try {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, imageUri)
                ImageDecoder.decodeBitmap(source)
            }
            _imageState.value = _imageState.value.copy(
                status = ImageState.Status.RegisterImageSuccess,
                message = "Image Select Success ...",
                registerImage = bitmap.asImageBitmap()
            )
        } catch (ex: Exception) {
            _imageState.value = _imageState.value.copy(
                status = ImageState.Status.RegisterImageFailed, message = "${ex.message}"
            )
        }
    }
}