package com.example.heartconnect.features.presentation.screens.profile.components.change_picture.viewmodel

data class ChangePictureState(
    val status: Status,
    val message: String? = null,
) {
    companion object {
        val IDLE = ChangePictureState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}