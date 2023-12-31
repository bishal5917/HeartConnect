package com.example.heartconnect.features.presentation.screens.feed.viewmodel

import com.example.heartconnect.features.data.models.feed.FeedModel

data class HomeState(
    val status: Status, val message: String? = null, val users: List<FeedModel>? = null
) {
    companion object {
        val IDLE = HomeState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}