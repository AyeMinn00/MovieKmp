package com.amo.moviekmp.domain.login

import com.amo.moviekmp.isStrongPassword
import com.amo.moviekmp.isValidatedEmail

data class LoginFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)

fun LoginFormState.updateEmail(email: String): LoginFormState {
    return copy(
        email = email.trim(),
        emailError = isValidatedEmail(email).errorMessage
    )
}

fun LoginFormState.updatePassword(password: String): LoginFormState {
    return copy(
        password = password.trim(),
        passwordError = isStrongPassword(password).errorMessage
    )
}

fun LoginFormState.isAbleToSubmit() = this.emailError == null && this.passwordError == null

fun LoginFormState.doesInputValidate(): Pair<LoginFormState, Boolean> {
    val emailValidation = isValidatedEmail(email)
    val pwdValidation = isStrongPassword(password)
    if (!emailValidation.successful || !pwdValidation.successful) {
        return Pair(
            copy(
                emailError = emailValidation.errorMessage,
                passwordError = pwdValidation.errorMessage,
            ), false
        )
    }
    return Pair(
        copy(), true
    )
}