package com.example.heartconnect.screens.login

data class LoginState(
    val status: Status, val message: String? = null, var email: String = "",
    var password: String = "",
    var emailError: Boolean = false,
    var passwordError: Boolean = false
) {
    companion object {
        val IDLE = LoginState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED, LOGOUTLOADING, LOGOUTSUCCESS, LOGOUTFAILED
    }
}
