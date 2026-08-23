package com.angelalonso.kanara.features.auth

import com.angelalonso.kanara.features.users.UsuarioRepository

expect class AuthService(usuarioRepository: UsuarioRepository) {
    suspend fun login(usuario: String, password: String): LoginResult
}