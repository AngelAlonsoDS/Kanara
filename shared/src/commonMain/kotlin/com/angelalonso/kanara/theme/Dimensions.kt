package com.angelalonso.kanara.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

object Spacing {
    val Small = 8.dp
    val Medium = 16.dp
    val Large = 24.dp
    val ExtraLarge = 32.dp
}

object FontSizes {
    val Emphasis = 16.sp
    val Body = 18.sp
    val Subheader = 20.sp

    val Header = 24.sp

    val Subtitle = 28.sp

    val Title = 30.sp
}

object RelativeFontSizes {
    val Scale60Percent = 0.6.em
    val Scale80Percent = 0.8.em
    val Increase10Percent = 1.1.em
}

object CornerRadii {
    val Small = 8.dp
    val Medium = 16.dp
    val Large = 24.dp
}

val AppShapes = Shapes(
    small = RoundedCornerShape(CornerRadii.Small),
    medium = RoundedCornerShape(CornerRadii.Medium),
    large = RoundedCornerShape(CornerRadii.Large),
)

object WindowBreakpoints {
    val Compact: Dp = 600.dp
    val Medium: Dp = 840.dp
    val Expanded: Dp = 1200.dp
}