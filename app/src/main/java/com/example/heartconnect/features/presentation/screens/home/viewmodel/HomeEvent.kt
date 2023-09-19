package com.example.heartconnect.features.presentation.screens.home.viewmodel

sealed class HomeEvent {
    data class GetFeed(val id: String) : HomeEvent()
}