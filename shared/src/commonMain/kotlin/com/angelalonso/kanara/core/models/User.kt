package com.angelalonso.kanara.core.models

import java.time.LocalDate

data class UserCredentials(val usuario: String, val password: String)

data class UserDTO(
    val idUser: Int,
    val fullName: String,
    val nameUser: String,
    val passwordHash: String,
    val role: String,
    val active: Int,
    val createAt: LocalDate,
    val updateAt: LocalDate
)