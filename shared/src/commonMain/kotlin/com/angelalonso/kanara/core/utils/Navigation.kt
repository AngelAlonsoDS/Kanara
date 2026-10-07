package com.angelalonso.kanara.core.utils


import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object Login : Route
    @Serializable data object Home : Route

    @Serializable data object PacienteList : Route
    @Serializable data class PacienteForm(val pacienteId: Long? = null) : Route
    @Serializable data object UsuarioList : Route
}