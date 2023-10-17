package com.example.heartconnect.features.presentation.screens.feed.components.single_feed.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.domain.usecases.GetSingleFeedUsecase
import com.example.heartconnect.model.CommonRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleFeedViewModel @Inject constructor(
    private val getSingleFeedUsecase:
    GetSingleFeedUsecase
) :
    ViewModel() {

    private val _singleFeedState = MutableStateFlow(SingleFeedState.IDLE)
    val singleFeedState = _singleFeedState.asStateFlow()

    fun onEvent(event: SingleFeedEvent) {
        when (event) {
            is SingleFeedEvent.GetSingleFeed -> {
                getSingleFeed(event.commonRequestModel)
            }
        }
    }

    private fun getSingleFeed(commonRequestModel: CommonRequestModel) = viewModelScope.launch {
        _singleFeedState.value = _singleFeedState.value.copy(
            status = SingleFeedState.Status.LOADING, message = "Getting details , please wait ..."
        )
        try {
            val result = getSingleFeedUsecase.call(commonRequestModel)
            _singleFeedState.value = _singleFeedState.value.copy(
                status = SingleFeedState.Status.SUCCESS,
                message = "Details fetched",
                feedData = result,
            )
        } catch (ex: Exception) {
            _singleFeedState.value = _singleFeedState.value.copy(
                status = SingleFeedState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}