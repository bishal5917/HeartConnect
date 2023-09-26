package com.example.heartconnect.utils

object Validator {
    private val regexPattern = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")

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

    fun validateName(value: String): ValidationResult {
        return ValidationResult(
            (value.isNotEmpty() && value.length >= 6)
        )
    }

    fun validateGender(value: String): ValidationResult {
        return ValidationResult(
            (value.isNotEmpty() && (value == "male" || value == "female"))
        )
    }

    fun validateBirthYear(value: String): ValidationResult {
        return ValidationResult(
            (value.isNotEmpty() && (value.toInt() in 1990..2005))
        )
    }

    fun validatePhone(value: String): ValidationResult {
        return ValidationResult(
            (value.isNotEmpty() && value.length == 10)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)