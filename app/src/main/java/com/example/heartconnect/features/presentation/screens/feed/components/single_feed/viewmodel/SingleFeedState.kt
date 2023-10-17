package com.example.heartconnect.features.presentation.screens.feed.components.single_feed.viewmodel

import com.example.heartconnect.features.data.models.feed.FeedModel

data class SingleFeedState(
    val status: Status, val message: String? = null, val feedData: FeedModel? = null
) {
    companion object {
        val IDLE = SingleFeedState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}