package com.angelalonso.kanara.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.angelalonso.kanara.core.utils.AppSnackbarVisuals
import com.angelalonso.kanara.core.utils.AppSnackbarVariant
import com.angelalonso.kanara.theme.AppShapes
import com.angelalonso.kanara.theme.ErrorColor
import com.angelalonso.kanara.theme.OnErrorColor
import com.angelalonso.kanara.theme.OnPrimaryColor
import com.angelalonso.kanara.theme.OnSuccessColor
import com.angelalonso.kanara.theme.OnWarningColor
import com.angelalonso.kanara.theme.PrimaryColor
import com.angelalonso.kanara.theme.Spacing
import com.angelalonso.kanara.theme.SuccessColor
import com.angelalonso.kanara.theme.WarningColor

@Composable
fun AppSnackbar(
    visuals: AppSnackbarVisuals,
    onDismiss: () -> Unit,
    onAction: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val (containerColor, contentColor, actionContentColor) = when (visuals.variant) {
        AppSnackbarVariant.INFORMATION -> Triple(
            OnPrimaryColor, Color.White, PrimaryColor
        )
        AppSnackbarVariant.SUCCESS -> Triple(
            OnSuccessColor, Color.White, SuccessColor
        )
        AppSnackbarVariant.WARNING -> Triple(
            OnWarningColor, Color(0xFF202124), WarningColor
        )
        AppSnackbarVariant.ERROR -> Triple(
            OnErrorColor, Color.White, ErrorColor
        )
    }

    Snackbar(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
        shape = AppShapes.medium,
        containerColor = containerColor,
        contentColor = contentColor,
        actionContentColor = actionContentColor,
        dismissAction = {
            IconButton(onClick = onDismiss) {
                Icon(Icons.Default.Close, contentDescription = "Cerrar")
            }
        },
        action = visuals.actionLabel?.let { label ->
            {
                TextButton(onClick = { onAction?.invoke(); onDismiss() }) {
                    Text(label)
                }
            }
        }
    ) {
        Row {
            Icon(Icons.Default.Warning, contentDescription = "Cuidado!")

            Column {
                Text(text = visuals.title, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                Text(text = visuals.message, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}