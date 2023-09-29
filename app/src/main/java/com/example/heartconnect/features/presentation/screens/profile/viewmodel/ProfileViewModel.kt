package com.example.heartconnect.features.presentation.screens.profile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.domain.usecases.GetUserProfileUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUsecase: GetUserProfileUsecase
) : ViewModel() {
    private val _profileState = MutableStateFlow(ProfileState.IDLE)
    val profileState = _profileState.asStateFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetProfile -> {
                getProfile(event.id ?: "")
            }
        }
    }

    private fun getProfile(id: String) = viewModelScope.launch {
        _profileState.value = _profileState.value.copy(
            status = ProfileState.Status.LOADING, message = "Loading profile , please wait ..."
        )
        try {
            val result = getUserProfileUsecase.call(id)
            _profileState.value = _profileState.value.copy(
                status = ProfileState.Status.SUCCESS,
                message = "Loading profile , please wait .." + ".",
                user = result,
            )
        } catch (ex: Exception) {
            _profileState.value = _profileState.value.copy(
                status = ProfileState.Status.LOADING, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}