package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.model.CommonRequestModel

class GetSingleFeedUsecase(private val userRepository: UserRepository) :
    Usecase<FeedModel, CommonRequestModel> {
    override suspend fun call(params: CommonRequestModel): FeedModel {
        return userRepository.getSingleFeed(params)
    }
}