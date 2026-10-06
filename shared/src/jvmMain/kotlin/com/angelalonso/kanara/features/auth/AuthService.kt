package com.angelalonso.kanara.features.auth

import com.angelalonso.kanara.core.security.PasswordUtils
import com.angelalonso.kanara.features.users.UsuarioRepository

actual class AuthService actual constructor(
    private val usuarioRepository: UsuarioRepository
) {

    actual suspend fun login(
        usuario: String,
        password: String
    ): LoginResult {

        val row = usuarioRepository.findByUsuario(usuario)
            ?: return LoginResult.CredencialesInvalidas

        return if (
            PasswordUtils.verify(
                password,
                row.password_hash
            )
        ) {
            LoginResult.Success(
                userId = row.id,
                nombreCompleto = row.nombre_completo,
                rol = row.rol
            )
        } else {
            LoginResult.CredencialesInvalidas
        }
    }
}