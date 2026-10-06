package com.angelalonso.kanara.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.material3.SnackbarHost
import androidx.compose.ui.Modifier
import com.angelalonso.kanara.core.utils.AppSnackbarVariant
import com.angelalonso.kanara.core.utils.AppSnackbarVisuals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Alignment


// --- CompositionLocal ---
val LocalSnackbarHostState = staticCompositionLocalOf<SnackbarHostState> {
    error("SnackbarHostState no proporcionado")
}

// --- Extension para mostrar ---
fun SnackbarHostState.showAppSnackbar(
    title: String,
    message: String,
    variant: AppSnackbarVariant,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
) {
    // Lanza en su propio scope para no bloquear
    val scope = CoroutineScope(Dispatchers.Main)
    scope.launch {
        showSnackbar(
            visuals = AppSnackbarVisuals(
                title = title,
                message = message,
                variant = variant,
                actionLabel = actionLabel,
            )
        )
        if (actionLabel != null && onAction != null) {
            // Simplificado: en producción, manejar SnackbarResult
        }
    }
}

// --- Provider que envuelve toda la app ---
@Composable
fun AppSnackbarProvider(content: @Composable () -> Unit) {
    val hostState = remember { SnackbarHostState() }

    CompositionLocalProvider(LocalSnackbarHostState provides hostState) {
        Box(Modifier.fillMaxSize()) {
            content()

            SnackbarHost(
                hostState = hostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) { snackbarData ->

                (snackbarData.visuals as? AppSnackbarVisuals)?.let { appVisuals ->
                    AppSnackbar(
                        visuals = appVisuals,
                        onDismiss = {
                            snackbarData.dismiss()
                        }
                    )
                }
            }
        }
    }
}