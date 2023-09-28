package com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel

data class RegisterState(
    val status: Status,
    val message: String? = null,
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var gender: String = "",
    var birthYear: String = "",
    var password: String = "",
    var hobbies: ArrayList<String>? = ArrayList(),
    var nameError: Boolean = false,
    var emailError: Boolean = false,
    var phoneError: Boolean = false,
    var genderError: Boolean = false,
    var birthYearError: Boolean = false,
    var passwordError: Boolean = false,
    var firstStepError: Boolean = true
) {
    companion object {
        val IDLE = RegisterState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED
    }
}