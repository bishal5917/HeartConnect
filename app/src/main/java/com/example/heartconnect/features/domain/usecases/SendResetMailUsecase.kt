package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.user.UserModel
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.model.CommonResponseModel

class SendResetMailUsecase(private val userRepository: UserRepository) :
    Usecase<CommonResponseModel, UserModel> {
    override suspend fun call(params: UserModel): CommonResponseModel {
        return userRepository.sendResetMail(params)
    }
}