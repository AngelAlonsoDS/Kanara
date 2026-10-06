package com.angelalonso.kanara.features.auth

data class UserSession(
    val userId: Long,
    val nombreCompleto: String,
    val rol: String
)