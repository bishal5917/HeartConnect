package com.example.heartconnect.features.presentation.screens.profile.viewmodel

import com.example.heartconnect.features.data.models.user.UserModel

data class ProfileState(
    val status: Status, val message: String? = null, val user: UserModel? = null
) {
    companion object {
        val IDLE = ProfileState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }

    fun copyWith(
        status: Status?, message: String?, user: UserModel?
    ): ProfileState {
        return ProfileState(
            status = status ?: this.status,
            message = message ?: this.message,
            user = user ?: this.user,
        )
    }
}