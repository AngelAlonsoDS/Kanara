package com.angelalonso.kanara.features.auth

import at.favre.lib.crypto.bcrypt.BCrypt
import com.angelalonso.kanara.features.users.UsuarioRepository

actual class AuthService actual constructor(private val usuarioRepository: UsuarioRepository) {
    actual suspend fun login(usuario: String, password: String): LoginResult {
        val row = usuarioRepository.findByUsuario(usuario) ?: return LoginResult.CredencialesInvalidas
        val result = BCrypt.verifyer().verify(password.toCharArray(), row.password_hash)

        return if (result.verified) LoginResult.Success(row.id, row.nombre_completo)
        else LoginResult.CredencialesInvalidas

    }
}