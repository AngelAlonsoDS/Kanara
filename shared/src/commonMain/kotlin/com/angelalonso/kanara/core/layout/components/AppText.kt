package com.angelalonso.kanara.core.layout.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

enum class AppTextVariant {
    TITLE,
    SUBTITLE,
    HEADING,
    SUBHEADING,
    BODY,
    EMPHASIS
}

@Composable
fun AppText(
    text: String,
    variant: AppTextVariant = AppTextVariant.BODY,
    textAlign: TextAlign = TextAlign.Unspecified,

    modifier: Modifier = Modifier,
) {
    val style = when (variant) {
        AppTextVariant.TITLE ->
            MaterialTheme.typography.displayLarge
        AppTextVariant.SUBTITLE ->
            MaterialTheme.typography.headlineSmall
        AppTextVariant.HEADING ->
            MaterialTheme.typography.titleLarge
        AppTextVariant.SUBHEADING ->
            MaterialTheme.typography.titleMedium
        AppTextVariant.BODY ->
            MaterialTheme.typography.bodyMedium
        AppTextVariant.EMPHASIS ->
            MaterialTheme.typography.bodySmall
    }

    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        style = style,
    )
}