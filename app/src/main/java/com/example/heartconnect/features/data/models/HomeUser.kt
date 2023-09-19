package com.example.heartconnect.features.data.models

import androidx.compose.ui.graphics.painter.Painter

data class HomeUser(
    val uid: String? = "", val name: String? = "", val profileImage: String? = "", val birthYear:
    String? = "", val imageResource:
    Int? = null,
    val
    hobbies:
    List<String>? = null
)
