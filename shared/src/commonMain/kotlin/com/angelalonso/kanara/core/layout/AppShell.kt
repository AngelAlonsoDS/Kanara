package com.angelalonso.kanara.core.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.angelalonso.kanara.core.layout.components.AppText
import com.angelalonso.kanara.core.layout.components.AppTextVariant
import com.angelalonso.kanara.theme.AppShapes
import com.angelalonso.kanara.theme.PrimaryColor
import com.angelalonso.kanara.theme.Spacing

@Composable
fun AppShell(
    nameRoute: String?,
    drawer: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        drawer()

        Box(
            Modifier
                .fillMaxSize()
                .padding(Spacing.Large)
                .border(1.dp, PrimaryColor, AppShapes.medium)
                .clip(AppShapes.medium)
        ) {
            Column(
                Modifier.fillMaxSize().padding(Spacing.Medium),
                verticalArrangement = Arrangement.spacedBy(Spacing.Medium),
            ) {
                nameRoute?.let { AppText(it, variant = AppTextVariant.HEADING) }
                Box(
                    Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                ) {
                    content()
                }
            }
        }
    }
}