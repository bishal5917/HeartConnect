package com.example.heartconnect.features.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.domain.usecases.GetHomeUsersUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getHomeUsersUsecase: GetHomeUsersUsecase) :
    ViewModel() {

    private val _homeState = MutableStateFlow(HomeState.IDLE)
    val homeState: StateFlow<HomeState> = _homeState

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetFeed -> {
                getFeed(event.id ?: "")
            }
        }
    }

    private fun getFeed(id: String) = viewModelScope.launch {
        _homeState.value = _homeState.value.copy(
            status = HomeState.Status.LOADING, message = "Getting feed , please wait ..."
        )
        try {
            val result = getHomeUsersUsecase.call(id)
            _homeState.value = _homeState.value.copy(
                status = HomeState.Status.SUCCESS, message = "Feed fetched", users = result,
            )
        } catch (ex: Exception) {
            _homeState.value = _homeState.value.copy(
                status = HomeState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }

}
