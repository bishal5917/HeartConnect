package com.example.heartconnect.features.data.models.register

import android.net.Uri

data class UserRegisterModel(
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
    val gender: String,
    val birthYear: String,
    val image: Uri,
    val hobbies: ArrayList<String>,
)
