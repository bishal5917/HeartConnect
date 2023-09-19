package com.example.heartconnect.features.presentation.screens.home.viewmodel

import com.example.heartconnect.features.data.models.HomeUser

data class HomeState(
    val status: Status, val message: String? = null, val users: List<HomeUser>? = null
) {
    companion object {
        val IDLE = HomeState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}