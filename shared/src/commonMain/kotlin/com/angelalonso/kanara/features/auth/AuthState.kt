package com.angelalonso.kanara.features.auth

sealed interface AuthState {
    data object LoggedOut : AuthState

    data class LoggedIn(
        val session: UserSession
    ) : AuthState
}