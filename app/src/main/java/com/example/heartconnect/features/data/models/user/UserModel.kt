package com.example.heartconnect.features.data.models.user

data class UserModel(
    val id: String? = "",
    val name: String? = "",
    val email: String? = "",
    val birthYear: String? = "",
    val image: String? = "",
    val hobbies: List<String>? = null,
    val pics: List<String>? = null,
    val phone: String? = "",
    val gender: String? = ""
)