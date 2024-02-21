package com.amo.moviekmp

import com.amo.moviekmp.domain.ValidationResult

val emailPattern = Regex("^\\S+@\\S+\\.\\S+\$")

fun isValidatedEmail(email: String): ValidationResult {
    if (email.isBlank()) {
        return ValidationResult(
            successful = false,
            errorMessage = "The email can't be blank"
        )
    }
    if (!emailPattern.matches(email)) {
        return ValidationResult(
            successful = false,
            errorMessage = "That's not a valid email"
        )
    }
    return ValidationResult(
        successful = true,
        errorMessage = null
    )
}

fun isStrongPassword(pwd : String): ValidationResult {
    if (pwd.trim().length < 8) {
        return ValidationResult(
            successful = false,
            errorMessage = "The password can't be blank"
        )
    }
    return ValidationResult(
        successful = true,
        errorMessage = null
    )
}