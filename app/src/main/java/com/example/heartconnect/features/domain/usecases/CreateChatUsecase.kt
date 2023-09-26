package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.chat.ChatRequestModel
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.model.CommonResponseModel

class CreateChatUsecase(private val userRepository: UserRepository) :
    Usecase<CommonResponseModel, ChatRequestModel> {
    override suspend fun call(params: ChatRequestModel): CommonResponseModel {
        return userRepository.createChat(params)
    }
}