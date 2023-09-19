package com.example.heartconnect.features.data.models.conversation

data class ConversationModel(
    val convoId: String? = "",
    val members: List<String>? = listOf(),
    val friendName: String? = "",
    val friendImage: String? = "",
)
