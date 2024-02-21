package com.amo.moviekmp.domain

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)