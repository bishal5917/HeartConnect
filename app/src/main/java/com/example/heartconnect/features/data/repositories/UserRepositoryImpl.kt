package com.example.heartconnect.features.data.repositories

import com.example.heartconnect.features.data.datasources.UserRemoteDatasource
import com.example.heartconnect.features.data.models.chat.ChatRequestModel
import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.data.models.register.UserRegisterModel
import com.example.heartconnect.features.data.models.user.UserModel
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.model.CommonRequestModel
import com.example.heartconnect.model.CommonResponseModel

class UserRepositoryImpl(private val userDataSource: UserRemoteDatasource) : UserRepository {
    override suspend fun getHomeUsers(id: String): List<FeedModel> {
        try {
            return userDataSource.getFeed(id)
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

    override suspend fun sendResetMail(userModel: UserModel): CommonResponseModel {
        try {
            return userDataSource.sendResetMail(userModel)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getUserProfile(id: String): UserModel {
        try {
            return userDataSource.getUserProfile(id)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun changePicture(commonRequestModel: CommonRequestModel): CommonResponseModel {
        try {
            return userDataSource.changePicture(commonRequestModel)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun addPicture(commonRequestModel: CommonRequestModel): CommonResponseModel {
        try {
            return userDataSource.addPicture(commonRequestModel)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getSingleFeed(commonRequestModel: CommonRequestModel): FeedModel {
        try {
            return userDataSource.getSingleFeed(commonRequestModel)
        } catch (ex: Exception) {
            throw ex
        }
    }
}