package com.example.heartconnect.features.presentation.screens.register.viewmodel.step_viewmodel

data class StepState(
    val step: Int,
) {
    companion object {
        val IDLE = StepState(step = 0)
    }
}