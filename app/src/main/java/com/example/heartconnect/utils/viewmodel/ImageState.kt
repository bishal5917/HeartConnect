package com.example.heartconnect.utils.viewmodel

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap

data class ImageState(
    val status: Status,
    val message: String? = null,
    val registerImage: ImageBitmap? = null,
    val registerImageUri: Uri? = null,
) {
    companion object {
        val IDLE = ImageState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, RegisterImageLoading, RegisterImageSuccess, RegisterImageFailed
    }
}