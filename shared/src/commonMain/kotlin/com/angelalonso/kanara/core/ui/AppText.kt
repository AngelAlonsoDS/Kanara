package com.angelalonso.kanara.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angelalonso.kanara.theme.Spacing

enum class AppTextVariant {
    Title,
    Subtitle,
    Body
}

@Composable
fun AppText(
    text: String,
    variant: AppTextVariant = AppTextVariant.Body,
    modifier: Modifier = Modifier,
) {
    val style = when (variant) {
        AppTextVariant.Body ->
            MaterialTheme.typography.bodyMedium
        AppTextVariant.Title ->
            MaterialTheme.typography.titleMedium
        AppTextVariant.Subtitle ->
            MaterialTheme.typography.titleSmall
    }

    Text(
        text = text,
        modifier = modifier.padding(Spacing.Small),
        style = style,
    )
}