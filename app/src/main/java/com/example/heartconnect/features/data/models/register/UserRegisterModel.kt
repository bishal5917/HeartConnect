package com.example.heartconnect.features.data.models.register

import androidx.compose.ui.graphics.ImageBitmap

data class UserRegisterModel(
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
    val gender: String,
    val birthYear: String,
    val image: ImageBitmap,
    val hobbies: ArrayList<String>,
)
