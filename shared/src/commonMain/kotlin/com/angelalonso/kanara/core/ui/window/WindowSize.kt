package com.angelalonso.kanara.core.ui.window

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.unit.Dp
import com.angelalonso.kanara.core.ui.window.WindowSize.*
import com.angelalonso.kanara.theme.WindowBreakpoints

enum class WindowSize {
    Compact,
    Medium,
    Expanded
}

@Composable
fun rememberWindowSize() {
    return BoxWithConstraints {
        when {
            maxWidth < WindowBreakpoints.Compact ->
                Compact
            maxWidth < WindowBreakpoints.Expanded ->
                Medium
            else ->
                Expanded
        }
    }
}

fun WindowSize.formWidth(maxWidth: Dp): Dp {
    return when (this) {
        WindowSize.Compact ->
            maxWidth * 0.9f

        WindowSize.Medium ->
            maxWidth * 0.75f

        WindowSize.Expanded ->
            maxWidth * 0.5f
    }
}