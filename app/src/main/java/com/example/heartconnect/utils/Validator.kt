package com.example.heartconnect.utils

object Validator {
    val regexPattern = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")

    fun validateEmail(value: String): ValidationResult {
        return ValidationResult(
            (value.isNotEmpty() && regexPattern.matches(value))
        )
    }

    fun validatePassword(value: String): ValidationResult {
        return ValidationResult(
            (value.isNotEmpty() && value.length >= 6)
        )
    }

    fun validateFirstName(value: String): ValidationResult {
        return ValidationResult(
            (value.isNotEmpty() && value.length >= 3)
        )
    }

    fun validateLastName(value: String): ValidationResult {
        return ValidationResult(
            (value.isNotEmpty() && value.length >= 3)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)