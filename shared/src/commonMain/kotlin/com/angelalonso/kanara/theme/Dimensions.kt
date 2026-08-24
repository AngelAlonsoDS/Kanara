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
    val Small = 16.sp
    val Medium = 18.sp
    val Large = 20.sp
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

/*
class CornerRadii(
    private val additionalPadding: Dp = 0.dp
) {
    val Large = 28.dp + additionalPadding
    val Medium = 16.dp + additionalPadding
    val Small = 8.dp + additionalPadding
}
*/

val AppShapes = Shapes(
    small = RoundedCornerShape(CornerRadii.Small),
    medium = RoundedCornerShape(CornerRadii.Medium),
    large = RoundedCornerShape(CornerRadii.Large),
)