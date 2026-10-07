package com.angelalonso.kanara.core.layout.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.angelalonso.kanara.core.utils.LabelNameRoutes
import com.angelalonso.kanara.core.utils.Route
import com.angelalonso.kanara.theme.Spacing

@Composable
fun NavigationItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val variant = if (selected) AppButtonVariant.Primary else AppButtonVariant.NAVIGATION

    AppButton(
        text = text,
        textAlign = TextAlign.Left,
        modifier = Modifier.fillMaxWidth(),
        variant = variant,
        onClick = onClick,
    )
}

@Composable
fun AppDrawer(
    currentRoute: Route,
    onNavigate: (Route) -> Unit
) {
    Column(
        modifier = Modifier
            .width(240.dp)
            .fillMaxHeight()
            .padding(Spacing.Large),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomStart
        ) {
            AppText(
                text = "Kanara",
                variant = AppTextVariant.TITLE,
                textAlign = TextAlign.Left,
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth().weight(3f),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            for ((key, value) in LabelNameRoutes) {
                NavigationItem(
                    text = value,
                    selected = currentRoute == key,
                    onClick = {
                        onNavigate(key)
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomStart
        ) {
        AppText(
            "by Alonso",
            variant = AppTextVariant.EMPHASIS,
        )

        }

    }
}