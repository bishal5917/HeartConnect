package com.example.heartconnect.features.domain.repositories

import com.example.heartconnect.features.data.models.HomeUser

interface UserRepository {
    suspend fun getHomeUsers(id: String): List<HomeUser>
}