package com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.heartconnect.features.data.models.register.UserRegisterModel
import com.example.heartconnect.features.domain.usecases.GetMessagesUsecase
import com.example.heartconnect.features.domain.usecases.RegisterUserUsecase
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeState
import com.example.heartconnect.services.local.LocalDatastore
import com.example.heartconnect.utils.Validator
import com.example.heartconnect.utils.viewmodel.ImageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUsecase: RegisterUserUsecase, private val localDatastore: LocalDatastore
) : ViewModel() {
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
            is RegisterEvent.Register -> {
                registerUser(event.imageUri)
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

        val firstStepError =
            _registerState.value.nameError && _registerState.value.emailError && _registerState.value.phoneError && _registerState.value.birthYearError && _registerState.value.genderError
        _registerState.value = _registerState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            nameError = nameResult.status,
            genderError = genderResult.status,
            phoneError = phoneResult.status,
            birthYearError = birthYearResult.status,
            firstStepError = firstStepError,
        )
    }

    private fun addOrRemoveHobby(hobby: String) {
        val hobbies = _registerState.value.hobbies
        if (hobbies != null) {
            if (!hobbies.contains(hobby) && hobbies.size != 5) {
                hobbies.add(hobby)
                _registerState.value = _registerState.value.copy(
                    hobbies = hobbies,
                    hobbiesSize = hobbies.size
                )
            } else if (hobbies.contains(hobby)) {
                hobbies.remove(hobby)
                _registerState.value = _registerState.value.copy(
                    hobbies = hobbies,
                    hobbiesSize = hobbies.size
                )
            }
        }
    }

    private fun registerUser(image: Uri) = viewModelScope.launch {
        _registerState.value = _registerState.value.copy(
            status = RegisterState.Status.LOADING, message = "Registering ..."
        )
        try {
            val result = registerUserUsecase.call(
                UserRegisterModel(
                    name = _registerState.value.name,
                    email = _registerState.value.email,
                    phone = _registerState.value.phone,
                    gender = _registerState.value.gender,
                    birthYear = _registerState.value.birthYear,
                    password = _registerState.value.password,
                    hobbies = _registerState.value.hobbies ?: ArrayList(),
                    image = image,
                )
            )
            if (result.userId != null) {
                localDatastore.saveUserId(result.userId)
                _registerState.value = _registerState.value.copy(
                    status = RegisterState.Status.SUCCESS, message = result.message,
                )
            } else {
                _registerState.value = _registerState.value.copy(
                    status = RegisterState.Status.FAILED,
                    message = "Registered,Please Re-login." + "..",
                )
            }
        } catch (ex: Exception) {
            _registerState.value = _registerState.value.copy(
                status = RegisterState.Status.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception: ${ex.message}")
        }
    }
}
