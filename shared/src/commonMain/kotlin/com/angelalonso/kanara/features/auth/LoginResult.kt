package com.angelalonso.kanara.features.auth

sealed interface LoginResult {
    data class Success(val usuarioId: Long, val nombreCompleto: String) : LoginResult
    data object CredencialesInvalidas : LoginResult
}