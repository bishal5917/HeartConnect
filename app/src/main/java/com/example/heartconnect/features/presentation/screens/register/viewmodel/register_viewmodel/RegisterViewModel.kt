package com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.heartconnect.features.domain.usecases.GetMessagesUsecase
import com.example.heartconnect.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val getMessagesUsecase: GetMessagesUsecase) :
    ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState.IDLE)
    val registerState: StateFlow<RegisterState> = _registerState

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.NameChanged -> {
                _registerState.value = _registerState.value.copy(
                    name = event.name
                )
                validateInputData()
            }
            is RegisterEvent.EmailChanged -> {
                _registerState.value = _registerState.value.copy(
                    email = event.email
                )
                validateInputData()
            }
            is RegisterEvent.PasswordChanged -> {
                _registerState.value = _registerState.value.copy(
                    password = event.password
                )
                validateInputData()
            }
            is RegisterEvent.GenderChanged -> {
                _registerState.value = _registerState.value.copy(
                    gender = event.gender
                )
                validateInputData()
            }
            is RegisterEvent.BirthYearChanged -> {
                _registerState.value = _registerState.value.copy(
                    birthYear = event.birthYear
                )
                validateInputData()
            }
            is RegisterEvent.PhoneChanged -> {
                _registerState.value = _registerState.value.copy(
                    phone = event.phone
                )
                validateInputData()
            }
            is RegisterEvent.AddOrRemoveHobby -> {
                addOrRemoveHobby(event.hobby)
            }
        }
    }

    private fun validateInputData() {
        val nameResult = Validator.validateName(
            value = _registerState.value.name
        )

        val emailResult = Validator.validateEmail(
            value = _registerState.value.email
        )

        val passwordResult = Validator.validatePassword(
            value = _registerState.value.password
        )

        val genderResult = Validator.validateGender(
            value = _registerState.value.gender
        )

        val phoneResult = Validator.validatePhone(
            value = _registerState.value.phone
        )

        val birthYearResult = Validator.validateBirthYear(
            value = _registerState.value.birthYear
        )

        _registerState.value = _registerState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            nameError = nameResult.status,
            genderError = genderResult.status,
            phoneError = phoneResult.status,
            birthYearError = birthYearResult.status
        )
    }

    private fun addOrRemoveHobby(hobby: String) {
        val hobbies = _registerState.value.hobbies
        if (hobbies != null) {
            if (!hobbies.contains(hobby) && hobbies.size != 5) {
                hobbies.add(hobby)
                _registerState.value = _registerState.value.copy(
                    hobbies = hobbies
                )
            } else if (hobbies.contains(hobby)) {
                hobbies.remove(hobby)
                _registerState.value = _registerState.value.copy(
                    hobbies = hobbies
                )
            }
        }
    }
}