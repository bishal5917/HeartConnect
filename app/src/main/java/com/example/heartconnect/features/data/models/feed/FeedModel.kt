package com.example.heartconnect.features.data.models.feed

data class FeedModel(
    val uid: String? = "", val name: String? = "", val profileImage: String? = "",
    val birthYear:
    String? = "",
    val imageResource:
    Int? = null,
    val
    hobbies:
    List<String>? = null,
    val pics: List<String>? = null,
    val email: String? = "",
    val phone: String? = "",
    val gender: String? = ""
)
