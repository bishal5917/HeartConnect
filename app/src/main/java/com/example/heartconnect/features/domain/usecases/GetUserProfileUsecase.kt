package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.user.UserModel
import com.example.heartconnect.features.domain.repositories.UserRepository

class GetUserProfileUsecase(private val userRepository: UserRepository) :
    Usecase<UserModel, String> {
    override suspend fun call(params: String): UserModel {
        return userRepository.getUserProfile(params)
    }
}