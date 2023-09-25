package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.message.MessageRequestModel
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.model.CommonResponseModel

class SendMessageUsecase(private val userRepository: UserRepository) :
    Usecase<CommonResponseModel, MessageRequestModel> {
    override suspend fun call(params: MessageRequestModel): CommonResponseModel {
        return userRepository.sendMessage(params)
    }
}