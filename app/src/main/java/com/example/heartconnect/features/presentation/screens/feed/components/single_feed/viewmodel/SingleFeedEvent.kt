package com.example.heartconnect.features.presentation.screens.feed.components.single_feed.viewmodel

import com.example.heartconnect.model.CommonRequestModel

sealed class SingleFeedEvent {
    data class GetSingleFeed(val commonRequestModel: CommonRequestModel) : SingleFeedEvent()
}