package com.example.heartconnect.features.domain.repositories

import com.example.heartconnect.features.data.models.feed.FeedModel

interface UserRepository {
    suspend fun getHomeUsers(id: String): List<FeedModel>
}