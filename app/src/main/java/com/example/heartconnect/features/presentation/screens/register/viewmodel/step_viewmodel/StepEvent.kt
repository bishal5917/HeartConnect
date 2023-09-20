package com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel

sealed class StepEvent {
    object Increment : StepEvent()
    object Decrement : StepEvent()
    object Reset : StepEvent()
    data class Change(var toStep: Int) : StepEvent()
}