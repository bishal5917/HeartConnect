package com.example.heartconnect.features.data.models.message

data class MessageRequestModel(
    val convoId: String,
    val userId: String,
    val message: String?="",
    val dateTime: String?="",
)
