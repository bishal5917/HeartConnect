package com.example.heartconnect.features.presentation.screens.profile.components.add_picture.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.features.domain.usecases.AddPictureUsecase
import com.example.heartconnect.model.CommonRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPictureViewModel @Inject constructor(private val addPictureUsecase: AddPictureUsecase) :
    ViewModel() {
    private val _addPictureState = MutableStateFlow(AddPictureState.IDLE)
    val addPictureState: StateFlow<AddPictureState> = _addPictureState

    fun onEvent(event: AddPictureEvent) {
        when (event) {
            is AddPictureEvent.AddPic -> {
                addPicture(event.commonRequestModel)
            }

            else -> {}
        }
    }

    private fun addPicture(commonRequestModel: CommonRequestModel) = viewModelScope.launch {
        _addPictureState.value = _addPictureState.value.copy(
            status = AddPictureState.Status.LOADING, message = "Uploading , please wait ..."
        )
        try {
            val result = addPictureUsecase.call(commonRequestModel)
            _addPictureState.value = _addPictureState.value.copy(
                status = AddPictureState.Status.SUCCESS, message = result.message
            )
        } catch (ex: Exception) {
            _addPictureState.value = _addPictureState.value.copy(
                status = AddPictureState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}