package com.example.heartconnect.model

data class CommonResponseModel(
    val success: Boolean,
    val message: String,
    val userId: String? = null,
)
