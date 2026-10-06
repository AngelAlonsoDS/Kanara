package com.angelalonso.kanara.features.auth

sealed interface LoginResult {
    data class Success(
        val userId: Long,
        val nombreCompleto: String,
        val rol: String
    ) : LoginResult
    data object CredencialesInvalidas : LoginResult
}