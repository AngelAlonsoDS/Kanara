package com.angelalonso.kanara.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,

    secondary = Secondary,
    onSecondary = Color.White,

    tertiary = Tertiary,
    onTertiary = Color(0xFF35373F),

    error = Error,
    onError = Color.White,

    background = Background,
    onBackground = Color(0xFF22232A),

    surface = Surface,
    onSurface = Color(0xFF22232A),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFB8C4FF),
    onPrimary = Color(0xFF07184F),

    secondary = Color(0xFFA4D0E2),
    onSecondary = Color(0xFF003544),

    tertiary = Color(0xFFD9D1C8),
    onTertiary = Color(0xFF38322D),

    error = Color(0xFFFFB3C0),
    onError = Color(0xFF540012),

    background = Color(0xFF121318),
    onBackground = Color(0xFFE5E1E9),

    surface = Color(0xFF121318),
    onSurface = Color(0xFFE5E1E9),
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        if (darkTheme) {
            DarkColorScheme
        } else {
            LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content,
    )
}