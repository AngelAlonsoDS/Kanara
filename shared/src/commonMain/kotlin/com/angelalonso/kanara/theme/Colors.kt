package com.angelalonso.kanara.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFF243B8F) // Azul rey
val OnPrimaryColor = Color(0xFF1F3171)

val SecondaryColor = Color(0xFF24718F) // Azul cielo
val OnSecondaryColor = Color(0xFF174A5D)

val TertiaryColor = Color(0xFFB6B1AA) // Crema gris
val OnTertiaryColor = Color(0xFF35373F)

val ErrorColor = Color(0xFF8F243B) // Rojo
val OnErrorColor = Color(0xFF741227)

val WarningColor = Color(0xFFA78C2A) // Amarillo
val OnWarningColor = Color(0xFF967C1E)

val SuccessColor = Color(0xFF3B8F24) // Verde
val OnSuccessColor = Color(0xFF30801A)


val BackgroundColor = Color(0xFFFFF0C9) // Fondo crema claro
val SurfaceColor = Color(0xFFF9F2E7)

val TextBodyColor = Color(0xFF22232A) // Gris oscuro
val OnTextBodyColor = Color(0xFF000000)

val TextButtonColor = Color(0xFFFFF0C9) // Color del fondo TODO: Separado del color Surface para posible rediseño
val OnTextButtonColor = Color(0xFFDFD7BF)

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