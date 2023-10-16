package com.example.heartconnect.features.presentation.screens.feed.viewmodel

sealed class HomeEvent {
    data class GetFeed(val id: String) : HomeEvent()
}