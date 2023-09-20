package com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StepViewModel : ViewModel() {
    private val _stepState = MutableStateFlow(StepState.IDLE)
    val stepState: StateFlow<StepState> = _stepState

    fun onEvent(event: StepEvent) {
        when (event) {
            is StepEvent.Increment -> {
                increment()
            }
            is StepEvent.Decrement -> {
                decrement()
            }
            is StepEvent.Change -> {
                changeStep(event.toStep)
            }
            is StepEvent.Reset -> {
                reset()
            }
        }
    }

    private fun increment() {
        if (_stepState.value.step < 2) _stepState.value = _stepState.value.copy(
            step = _stepState.value.step + 1
        )
        Log.d("Step", "${_stepState.value.step}")
    }

    private fun decrement() {
        if (_stepState.value.step > 0) _stepState.value = _stepState.value.copy(
            step = _stepState.value.step - 1
        )
        Log.d("Step", "${_stepState.value.step}")
    }

    private fun changeStep(step: Int) {
        if (_stepState.value.step != step) _stepState.value = _stepState.value.copy(
            step = step
        )
    }

    private fun reset() {
        _stepState.value = _stepState.value.copy(
            step = 0
        )
    }
}