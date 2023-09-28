package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.register.UserRegisterModel
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.model.CommonResponseModel

class RegisterUserUsecase(private val userRepository: UserRepository) :
    Usecase<CommonResponseModel, UserRegisterModel> {
    override suspend fun call(params: UserRegisterModel): CommonResponseModel {
        return userRepository.registerUser(params)
    }
}