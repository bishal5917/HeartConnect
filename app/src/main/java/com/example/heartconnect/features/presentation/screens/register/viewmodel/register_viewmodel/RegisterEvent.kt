package com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel

import android.net.Uri

sealed class RegisterEvent {
    data class NameChanged(val name: String) : RegisterEvent()
    data class EmailChanged(val email: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()
    data class GenderChanged(val gender: String) : RegisterEvent()
    data class BirthYearChanged(val birthYear: String) : RegisterEvent()
    data class PhoneChanged(val phone: String) : RegisterEvent()
    data class AddOrRemoveHobby(val hobby: String) : RegisterEvent()

    data class Register(val imageUri: Uri) : RegisterEvent()
}