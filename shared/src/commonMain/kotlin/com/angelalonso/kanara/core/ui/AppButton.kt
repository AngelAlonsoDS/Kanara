package com.angelalonso.kanara.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

enum class AppButtonVariant {
    Primary,
    Secondary,
    Tertiary,
}

@Composable
fun AppButton(
    variant: AppButtonVariant = AppButtonVariant.Primary,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
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
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
            )
    }

    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = colors,
        content = content,
    )
}