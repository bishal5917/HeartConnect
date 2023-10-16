package com.example.heartconnect.features.presentation.screens.profile.components.add_picture.viewmodel

import com.example.heartconnect.model.CommonRequestModel

sealed class AddPictureEvent {
    data class AddPic(val commonRequestModel: CommonRequestModel) : AddPictureEvent()
}