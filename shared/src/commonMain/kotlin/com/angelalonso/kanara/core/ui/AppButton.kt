package com.angelalonso.kanara.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.angelalonso.kanara.theme.Spacing

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
    text: String
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
    ) {
        Text(text, modifier = Modifier.padding(Spacing.Small), color = Color.White)
    }
}