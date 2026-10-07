package com.angelalonso.kanara.core.layout.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.angelalonso.kanara.core.utils.Route

@Composable
fun NavigationItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val variant = if (selected) AppButtonVariant.Primary else AppButtonVariant.Tertiary

    AppButton(
        text = text,
        variant = variant,
        onClick = onClick,
    )
}

@Composable
fun AppDrawer(
    currentRoute: Route,
    onNavigate: (Route) -> Unit
) {
    Column(
        modifier = Modifier
            .width(240.dp)
            .fillMaxHeight()
    ) {
        AppText(
            text = "Kanara",
            variant = AppTextVariant.TITLE,
            textAlign = TextAlign.Left,
        )

        NavigationItem(
            text = "Pacientes",
            selected = currentRoute == Route.PacienteList,
            onClick = {
                onNavigate(Route.PacienteList)
            }
        )

        NavigationItem(
            text = "Usuarios",
            selected = currentRoute == Route.UsuarioList,
            onClick = {
                onNavigate(Route.UsuarioList)
            }
        )

        AppText(
            "by Alonso",
            variant = AppTextVariant.EMPHASIS,
        )
    }
}