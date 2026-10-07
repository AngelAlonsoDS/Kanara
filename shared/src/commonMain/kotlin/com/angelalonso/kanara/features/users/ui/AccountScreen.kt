package com.angelalonso.kanara.features.users.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angelalonso.kanara.core.layout.components.AppText
import com.angelalonso.kanara.core.layout.components.AppTextField
import com.angelalonso.kanara.core.ui.LocalUserSession
import com.angelalonso.kanara.theme.Spacing

@Composable
fun AccountScreen() {
    val session = LocalUserSession.current

    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Medium),
    ) {
        if (session != null) {
            AppText("Nombre completo")
            AppTextField(value = session.nombreCompleto, enabled = false, onValueChange = {})

            AppText("Rol de usuario")
            AppTextField(value = session.rol, enabled = false, onValueChange = {})
        }
    }

}