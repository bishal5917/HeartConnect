package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.domain.repositories.UserRepository

class GetHomeUsersUsecase (private val userRepository: UserRepository) :
    Usecase<List<FeedModel>, String> {
    override suspend fun call(params: String): List<FeedModel> {
        return userRepository.getHomeUsers(params)
    }
}