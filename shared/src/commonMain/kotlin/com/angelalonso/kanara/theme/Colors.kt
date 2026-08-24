package com.angelalonso.kanara.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF243B8F) // Azul rey
val OnPrimary = Color(0xFF1F3171)

val Secondary = Color(0xFF24718F) // Azul cielo
val OnSecondary = Color(0xFF174A5D)

val Tertiary = Color(0xFFB6B1AA) // Crema gris
val OnTertiary = Color(0xFF35373F)

val Error = Color(0xFF8F243B) // Rojo
val OnError = Color(0xFF741227)

val Warning = Color(0xFFA78C2A) // Amarillo
val OnWarning = Color(0xFF967C1E)

val Success = Color(0xFF3B8F24) // Verde
val OnSuccess = Color(0xFF30801A)


val Surface = Color(0xFFFFF9EA) // Fondo crema claro
val OnSurface = Color(0xFFFFF0C9)

val TextBody = Color(0xFF22232A) // Gris oscuro
val OnTextBody = Color(0xFF000000)

val TextButton = Color(0xFFFFF9EA) // Color del fondo TODO: Separado del color Surface para posible rediseño
val OnTextButton = Color(0xFFDFD7BF)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF243B8F),
    onPrimary = Color(0xFF1F3171),

    secondary = Color(0xFF24718F),
    onSecondary = Color(0xFF174A5D),

    tertiary = Color(0xFFB6B1AA),
    onTertiary = Color(0xFF35373F),

    error = Color(0xFF8F243B),
    onError = Color(0xFF741227),

    surface = Color(0xFFFFF9EA),
    onSurface = Color(0xFFFFF0C9),

    background = Color(0xFFFFF9EA),
    onBackground = Color(0xFF22232A),
)

data class ExtendedColors(
    val warning: Color,
    val onWarning: Color,
    val success: Color,
    val onSuccess: Color,

    val textBody: Color,
    val onTextBody: Color,

    val textButton: Color,
    val onTextButton: Color,
)

val LightExtendedColors = ExtendedColors(
    warning = Color(0xFFA78C2A),
    onWarning = Color(0xFF967C1E),

    success = Color(0xFF3B8F24),
    onSuccess = Color(0xFF30801A),

    textBody = Color(0xFF22232A),
    onTextBody = Color(0xFF000000),

    textButton = Color(0xFFFFF9EA),
    onTextButton = Color(0xFFDFD7BF),
)