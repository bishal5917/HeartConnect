package com.example.heartconnect.features.data.models

import androidx.compose.ui.graphics.painter.Painter

data class HomeUser(
    val uid: String, val name: String, val birthYear: String, val imageResource: Int, val
    hobbies:
    List<String>
)
