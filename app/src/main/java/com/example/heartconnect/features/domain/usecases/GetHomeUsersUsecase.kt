package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.HomeUser
import com.example.heartconnect.features.domain.repositories.UserRepository
import javax.inject.Inject

class GetHomeUsersUsecase (private val userRepository: UserRepository) :
    Usecase<List<HomeUser>, String> {
    override suspend fun call(params: String): List<HomeUser> {
        return userRepository.getHomeUsers(params)
    }
}