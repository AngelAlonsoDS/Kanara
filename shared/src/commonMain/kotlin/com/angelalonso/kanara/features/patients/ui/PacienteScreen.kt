package com.angelalonso.kanara.features.patients.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.angelalonso.kanara.core.layout.components.AppText
import com.angelalonso.kanara.core.layout.components.AppTextVariant
import com.angelalonso.kanara.core.ui.LocalUserSession

@Composable
fun PacienteScreen() {

    val session = LocalUserSession.current

    Column {
        AppText(
            text = "Bienvenido, ${session?.nombreCompleto}",
            variant = AppTextVariant.SUBTITLE
        )

        AppText(
            text = "Rol: ${session?.rol}"
        )
    }
}