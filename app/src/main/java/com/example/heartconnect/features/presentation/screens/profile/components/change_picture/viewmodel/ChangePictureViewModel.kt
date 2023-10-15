package com.example.heartconnect.features.presentation.screens.profile.components.change_picture.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.domain.usecases.ChangePictureUsecase
import com.example.heartconnect.model.CommonRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePictureViewModel @Inject constructor(
    private val changePictureUsecase: ChangePictureUsecase
) : ViewModel() {
    private val _changePictureState = MutableStateFlow(ChangePictureState.IDLE)
    val changePictureState: StateFlow<ChangePictureState> = _changePictureState

    fun onEvent(event: ChangePictureEvent) {
        when (event) {
            is ChangePictureEvent.ChangePicture -> {
                changePicture(event.commonRequestModel)
            }

            else -> {}
        }
    }

    private fun changePicture(commonRequestModel: CommonRequestModel) = viewModelScope.launch {
        _changePictureState.value = _changePictureState.value.copy(
            status = ChangePictureState.Status.LOADING, message = "Requesting , please wait ..."
        )
        try {
            val result = changePictureUsecase.call(commonRequestModel)
            _changePictureState.value = _changePictureState.value.copy(
                status = ChangePictureState.Status.SUCCESS, message = result.message
            )
        } catch (ex: Exception) {
            _changePictureState.value = _changePictureState.value.copy(
                status = ChangePictureState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}