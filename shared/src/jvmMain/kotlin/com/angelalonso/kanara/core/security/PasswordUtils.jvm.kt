package com.angelalonso.kanara.core.security

import at.favre.lib.crypto.bcrypt.BCrypt

actual object PasswordUtils {

    private const val COST = 12

    actual fun hash(password: String): String {
        return BCrypt.withDefaults()
            .hashToString(COST, password.toCharArray())
    }

    actual fun verify(
        password: String,
        hash: String
    ): Boolean {
        return BCrypt.verifyer()
            .verify(password.toCharArray(), hash)
            .verified
    }
}