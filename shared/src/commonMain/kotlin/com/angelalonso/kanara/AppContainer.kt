package com.angelalonso.kanara

import com.angelalonso.kanara.db.DatabaseDriverFactory
import com.angelalonso.kanara.db.DatabaseInitializer
import com.angelalonso.kanara.features.auth.AuthService
import com.angelalonso.kanara.features.patients.PacienteRepository
import com.angelalonso.kanara.features.users.UsuarioRepository

class AppContainer(driverFactory: DatabaseDriverFactory) {
    val databaseInitializer = DatabaseInitializer(driverFactory)

    val usuarioRepository = UsuarioRepository(databaseInitializer)
    val authService = AuthService(usuarioRepository)

    val pacienteRepository = PacienteRepository(databaseInitializer)
}