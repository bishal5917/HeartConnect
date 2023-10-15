package com.example.heartconnect.features.domain.usecases

import com.example.heartconnect.features.data.models.register.UserRegisterModel
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.model.CommonRequestModel
import com.example.heartconnect.model.CommonResponseModel

class ChangePictureUsecase(private val userRepository: UserRepository) :
    Usecase<CommonResponseModel, CommonRequestModel> {
    override suspend fun call(params: CommonRequestModel): CommonResponseModel {
        return userRepository.changePicture(params)
    }
}