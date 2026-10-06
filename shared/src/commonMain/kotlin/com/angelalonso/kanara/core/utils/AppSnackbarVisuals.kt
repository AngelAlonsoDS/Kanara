package com.angelalonso.kanara.core.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

enum class AppSnackbarVariant {
    INFORMATION,
    SUCCESS,
    WARNING,
    ERROR
}

data class AppSnackbarVisuals(
    val title: String,
    val variant: AppSnackbarVariant,
    override val message: String,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val withDismissAction: Boolean = false,
) : SnackbarVisuals
