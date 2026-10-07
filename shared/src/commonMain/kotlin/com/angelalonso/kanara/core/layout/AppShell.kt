package com.angelalonso.kanara.core.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.angelalonso.kanara.theme.PrimaryColor
import com.angelalonso.kanara.theme.Spacing

@Composable
fun AppShell(
    drawer: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize().padding(Spacing.Medium)
    ) {
        drawer()

        Box(
            Modifier
                .fillMaxSize()
                .padding(Spacing.Medium)
                .border(1.dp, PrimaryColor, CircleShape)
        ) {
            content()
        }
    }
}