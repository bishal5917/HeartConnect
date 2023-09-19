package com.example.heartconnect.features.data.repositories

import com.example.heartconnect.features.data.datasources.UserRemoteDatasource
import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.domain.repositories.UserRepository

class UserRepositoryImpl(private val userDataSource: UserRemoteDatasource) :
    UserRepository {
    override suspend fun getHomeUsers(id: String): List<FeedModel> {
        try {
            return userDataSource.getHomeUsers(id)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getConversations(id: String): List<ConversationModel> {
        try {
            return userDataSource.getConversations(id)
        } catch (ex: Exception) {
            throw ex
        }
    }
}