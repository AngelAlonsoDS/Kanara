package com.angelalonso.kanara.core.utils


import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object Login : Route
    @Serializable data object Home : Route
    @Serializable data object Account : Route
    @Serializable data object Settings : Route
    @Serializable data object PacienteList : Route
    @Serializable data class PacienteForm(val pacienteId: Long? = null) : Route
    @Serializable data object UsuarioList : Route
}


val LabelNameRoutes = mapOf<Route, String>(
    Route.PacienteList to "Pacientes",
    Route.UsuarioList to "Usuarios",
    Route.PacienteForm() to "Formulario de paciente",
    Route.Account to "Cuenta",
    Route.Settings to "Configuracion"
)