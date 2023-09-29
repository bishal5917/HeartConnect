package com.example.heartconnect.features.presentation.screens.login

data class LoginState(
    val status: Status? = Status.IDLE,
    val message: String? = null,
    var email: String = "",
    var password: String = "",
    var emailError: Boolean = false,
    var passwordError: Boolean = false
) {
    companion object {
        val IDLE = LoginState(Status.IDLE, message = "Idle")
    }

    enum class Status {
        IDLE, LOADING, SUCCESS, FAILED, LOGOUTLOADING, LOGOUTSUCCESS, LOGOUTFAILED, ResetPasswordLoading, ResetPasswordSuccess, ResetPasswordFailure
    }

    fun copyWith(
        status: Status? = null,
        message: String? = null,
        email: String? = null,
        password: String? = null,
        emailError: Boolean? = null,
        passwordError: Boolean? = null
    ): LoginState {
        return LoginState(
            status ?: this.status,
            message ?: this.message,
            email ?: this.email,
            password ?: this.password,
            emailError ?: this.emailError,
            passwordError ?: this.passwordError
        )
    }
}
