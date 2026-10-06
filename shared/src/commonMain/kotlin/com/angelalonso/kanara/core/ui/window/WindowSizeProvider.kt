package com.angelalonso.kanara.core.ui.window

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angelalonso.kanara.theme.WindowBreakpoints

@Composable
fun WindowSizeProvider(
    content: @Composable (WindowSize) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val windowSize = when {
            maxWidth < WindowBreakpoints.Compact ->
                WindowSize.Compact

            maxWidth < WindowBreakpoints.Expanded ->
                WindowSize.Medium

            else ->
                WindowSize.Expanded
        }

        content(windowSize)
    }
}