package com.example.heartconnect.features.presentation.screens.splash.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.local_datastore.LocalDatastore
import com.example.heartconnect.utils.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val localDatastore: LocalDatastore) :
    ViewModel() {

    private val _splashState = MutableStateFlow(SplashState.IDLE)
    val splashState: StateFlow<SplashState> = _splashState

    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.CheckStatus -> {
                checkStatus()
            }
        }
    }

    val userId = localDatastore.getUserId().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    );

    private fun checkStatus() = viewModelScope.launch {
        try {
            val userId = localDatastore.getUserId().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = ""
            );
            if (userId.value.isEmpty()) {
                _splashState.value = _splashState.value.copy(
                    status = SplashState.SplashStatus.LOGGEDOUT, message = "User logged out"
                )
                Log.d("Splash", "User ID: ${userId.value}")
            } else {
                _splashState.value = _splashState.value.copy(
                    status = SplashState.SplashStatus.LOGGEDIN, message = "User Logged In"
                )
                Log.d("Splash", "User ID: ${userId.value}")
            }
        } catch (ex: Exception) {
            _splashState.value = _splashState.value.copy(
                status = SplashState.SplashStatus.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Login Exception: ${ex.message}")
        }
    }
}