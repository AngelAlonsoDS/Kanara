package com.angelalonso.kanara.core.layout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.angelalonso.kanara.theme.AppShapes
import com.angelalonso.kanara.theme.OnTertiaryColor
import com.angelalonso.kanara.theme.PrimaryColor
import com.angelalonso.kanara.theme.Spacing

enum class AppButtonVariant {
    Primary,
    Secondary,
    Tertiary,
    NAVIGATION
}

@Composable
fun AppButton(
    variant: AppButtonVariant = AppButtonVariant.Primary,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    textAlign: TextAlign = TextAlign.Unspecified,
) {
    val colors = when (variant) {
        AppButtonVariant.Primary ->
            buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            )

        AppButtonVariant.Secondary ->
            buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
            )

        AppButtonVariant.Tertiary ->
            buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onTertiary,
            )
        AppButtonVariant.NAVIGATION ->
            buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
            )
    }

    val color = when (variant) {
        AppButtonVariant.Primary, AppButtonVariant.Secondary ->
            Color.White

        AppButtonVariant.Tertiary ->
            OnTertiaryColor

        AppButtonVariant.NAVIGATION ->
            PrimaryColor
    }

    Button(
        modifier = if (variant == AppButtonVariant.Tertiary) modifier.border(1.dp, OnTertiaryColor, AppShapes.small) else modifier,
        enabled = enabled,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = colors,
    ) {
        Text(
            text,
            modifier = Modifier.padding(Spacing.Small),
            textAlign = textAlign,
            color = color
        )
    }
}