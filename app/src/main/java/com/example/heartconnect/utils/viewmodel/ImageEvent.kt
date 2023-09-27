package com.example.heartconnect.utils.viewmodel

import android.content.Context
import android.net.Uri

sealed class ImageEvent {
    data class SelectRegisterImage(val imageUri: Uri, val context: Context) : ImageEvent()
}