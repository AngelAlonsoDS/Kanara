package com.angelalonso.kanara.db

import com.angelalonso.kanara.core.security.PasswordUtils

class DatabaseSeeder(
    private val database: AppDatabase
) {

    fun seed() {
        val queries = database.appDatabaseQueries

        seedAdmin(queries)
        seedDevelopmentUsers(queries)
    }

    private fun seedAdmin(queries: AppDatabaseQueries) {
        if (queries.selectByUsuario("admin").executeAsOneOrNull() != null) {
            return
        }

        queries.insertUsuario(
            nombre_completo = "Administrador",
            usuario = "admin",
            password_hash = PasswordUtils.hash("admin123"),
            rol = "admin"
        ).executeAsOne()
    }

    private fun seedDevelopmentUsers(queries: AppDatabaseQueries) {
        if (queries.selectByUsuario("jperez").executeAsOneOrNull() == null) {
            queries.insertUsuario(
                nombre_completo = "Dr. Juan Pérez",
                usuario = "jperez",
                password_hash = PasswordUtils.hash("medico123"),
                rol = "medico"
            ).executeAsOne()
        }
    }
}