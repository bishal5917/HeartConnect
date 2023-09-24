package com.example.heartconnect.features.data.models.message

data class MessageModel(
    val timeStamp: String? = "",
    val friendName: String? = "",
    val friendImage: String? = "",
    val message: String? = "",
    val senderId: String? = "",
)
