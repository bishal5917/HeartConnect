package com.example.heartconnect.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.utils.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState.IDLE)
    val loginState: StateFlow<LoginState> = _loginState

    private val auth: FirebaseAuth = Firebase.auth

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _loginState.value = _loginState.value.copy(
                    email = event.email
                )
                validateInputData()
            }

            is LoginEvent.PasswordChanged -> {
                _loginState.value = _loginState.value.copy(
                    password = event.password
                )
                validateInputData()
            }
            is LoginEvent.LoginUser -> {
                login()
            }
        }
    }

    private fun validateInputData() {
        val emailResult = Validator.validateEmail(
            value = _loginState.value.email
        )

        val passwordResult = Validator.validatePassword(
            value = _loginState.value.password
        )

        _loginState.value = _loginState.value.copy(
            emailError = emailResult.status, passwordError = passwordResult.status
        )
    }

    private fun login() = viewModelScope.launch {
        Log.d("LOGIN", _loginState.value.email)
        Log.d("LOGIN", _loginState.value.password)

        _loginState.value = _loginState.value.copy(
            status = LoginState.Status.LOADING, message = "Logging in, Please wait ..."
        )
        try {
            val result =
                auth.signInWithEmailAndPassword(_loginState.value.email, _loginState.value.password)
                    .await()
            if (result.user != null) {
                _loginState.value = _loginState.value.copy(
                    status = LoginState.Status.SUCCESS, message = "Logged In Successfully"
                )
                Log.d("logs", "Login successful")
            } else {
                _loginState.value = _loginState.value.copy(
                    status = LoginState.Status.FAILED, message = "Login failed"
                )
                Log.d("logs", "Login failed")
            }
        } catch (ex: Exception) {
            _loginState.value = _loginState.value.copy(
                status = LoginState.Status.FAILED, message = ex.message ?: "An error occurred"
            )
            Log.d("logs", "Login Exception: ${ex.message}")
        }
    }
}