package com.example.heartconnect.features.presentation.screens.profile.components.add_picture.viewmodel

data class AddPictureState(
    val status: Status,
    val message: String? = null,
) {
    companion object {
        val IDLE = AddPictureState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}