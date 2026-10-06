package com.angelalonso.kanara.core.security

expect object PasswordUtils {

    fun hash(password: String): String

    fun verify(
        password: String,
        hash: String
    ): Boolean
}