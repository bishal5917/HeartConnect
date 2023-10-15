package com.example.heartconnect.features.presentation.screens.profile.components.change_picture.viewmodel

import com.example.heartconnect.model.CommonRequestModel

sealed class ChangePictureEvent {
    data class ChangePicture(val commonRequestModel: CommonRequestModel) : ChangePictureEvent()
}