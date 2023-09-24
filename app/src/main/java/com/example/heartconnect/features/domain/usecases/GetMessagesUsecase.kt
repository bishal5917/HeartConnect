package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.data.models.message.MessageModel
import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.domain.repositories.UserRepository

class GetMessagesUsecase(private val userRepository: UserRepository) :
    Usecase<List<MessageModel>, MessageRequestModel> {
    override suspend fun call(params: MessageRequestModel): List<MessageModel> {
        return userRepository.getMessages(params)
    }
}