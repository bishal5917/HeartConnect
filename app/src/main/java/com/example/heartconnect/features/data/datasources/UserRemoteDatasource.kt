package com.example.heartconnect.features.data.datasources

import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel

interface UserRemoteDatasource {
    suspend fun getHomeUsers(id: String): List<FeedModel>

    suspend fun getConversations(id : String): List<ConversationModel>

}