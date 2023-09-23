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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val localDatastore: LocalDatastore) :
    ViewModel() {

    private val _splashState = MutableStateFlow(SplashState.IDLE)
    val splashState: StateFlow<SplashState> = _splashState

     val userIdFlow = localDatastore.getUserId().stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    );

    init {
        viewModelScope.launch {
            userIdFlow.collect()
        }
    }

    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.CheckStatus -> {
                checkStatus()
            }
        }
    }


    private fun checkStatus() = viewModelScope.launch {
        try {
            if (userIdFlow.value.isEmpty()) {
                _splashState.value = _splashState.value.copy(
                    status = SplashState.SplashStatus.LOGGEDOUT, message = "User logged out"
                )
                Log.d("Splash", "ID: ${userIdFlow.value}")
            } else {
                _splashState.value = _splashState.value.copy(
                    status = SplashState.SplashStatus.LOGGEDIN, message = "User Logged In"
                )
                Log.d("Splash", "ID: ${userIdFlow.value}")
            }
        } catch (ex: Exception) {
            _splashState.value = _splashState.value.copy(
                status = SplashState.SplashStatus.FAILED, message = "${ex.message}"
            )
            Log.d("logs", "Exception : ${ex.message}")
        }
    }
}