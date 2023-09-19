package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.conversation.ConversationModel
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.domain.repositories.UserRepository

class GetConversationsUsecase(private val userRepository: UserRepository) :
    Usecase<List<ConversationModel>, String> {
    override suspend fun call(params: String): List<ConversationModel> {
        return userRepository.getConversations(params)
    }
}