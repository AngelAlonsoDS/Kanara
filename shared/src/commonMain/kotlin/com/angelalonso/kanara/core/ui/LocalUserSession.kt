package com.angelalonso.kanara.core.ui


import androidx.compose.runtime.compositionLocalOf
import com.angelalonso.kanara.features.auth.UserSession

val LocalUserSession = compositionLocalOf<UserSession?> {
    null
}