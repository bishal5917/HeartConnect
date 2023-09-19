package com.example.heartconnect.features.data.datasources

import com.example.heartconnect.features.data.models.feed.FeedModel

interface UserRemoteDatasource {
    suspend fun getHomeUsers(id: String): List<FeedModel>
}