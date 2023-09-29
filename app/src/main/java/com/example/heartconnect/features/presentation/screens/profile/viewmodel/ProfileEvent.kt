package com.example.heartconnect.features.presentation.screens.profile.viewmodel

sealed class ProfileEvent {
    data class GetProfile(val id: String) : ProfileEvent()
}