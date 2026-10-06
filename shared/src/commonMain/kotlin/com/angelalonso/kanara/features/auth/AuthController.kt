package com.angelalonso.kanara.features.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AuthController(
    private val authService: AuthService
) {
    var authState by mutableStateOf<AuthState>(AuthState.LoggedOut)
        private set

    suspend fun login(usuario: String, password: String) {
        when (val result = authService.login(usuario, password)) {
            is LoginResult.Success -> {
                authState = AuthState.LoggedIn(
                    UserSession(
                        userId = result.userId,
                        nombreCompleto = result.nombreCompleto,
                        rol = result.rol
                    )
                )
            }

            LoginResult.CredencialesInvalidas -> {
                authState = AuthState.LoggedOut
            }
        }
    }

    fun logout() {
        authState = AuthState.LoggedOut
    }
}