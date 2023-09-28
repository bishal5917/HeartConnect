package com.example.heartconnect.features.data.repositories

import com.example.heartconnect.features.data.datasources.UserRemoteDatasource
import com.example.heartconnect.features.data.models.chat.ChatRequestModel
import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.data.models.register.UserRegisterModel
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.model.CommonResponseModel

class UserRepositoryImpl(private val userDataSource: UserRemoteDatasource) : UserRepository {
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

    override suspend fun getMessages(messageRequestModel: MessageRequestModel): List<MessageModel> {
        try {
            return userDataSource.getMessages(messageRequestModel)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun sendMessage(messageRequestModel: MessageRequestModel): CommonResponseModel {
        try {
            return userDataSource.sendMessage(messageRequestModel)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun createChat(chatRequestModel: ChatRequestModel): CommonResponseModel {
        try {
            return userDataSource.createChat(chatRequestModel)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun registerUser(userRegisterModel: UserRegisterModel): CommonResponseModel {
        try {
            return userDataSource.registerUser(userRegisterModel)
        } catch (ex: Exception) {
            throw ex
        }
    }
}