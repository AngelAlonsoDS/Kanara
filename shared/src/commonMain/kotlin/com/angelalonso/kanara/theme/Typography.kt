package com.angelalonso.kanara.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kanara.shared.generated.resources.Baloo2_Bold
import kanara.shared.generated.resources.Baloo2_Medium
import kanara.shared.generated.resources.Baloo2_Regular
import kanara.shared.generated.resources.Nunito_Bold
import kanara.shared.generated.resources.Nunito_Italic
import kanara.shared.generated.resources.Nunito_Medium
import kanara.shared.generated.resources.Res
import org.jetbrains.compose.resources.Font

object AppFonts {

    val Nunito: FontFamily
        @Composable
        get() = FontFamily(
            Font(
                Res.font.Nunito_Medium,
                FontWeight.Medium
            ),
            Font(
                Res.font.Nunito_Bold,
                FontWeight.Bold
            ),
            Font(
                Res.font.Nunito_Italic,
                FontWeight.Normal,
                FontStyle.Italic
            ),
        )

    val Baloo2: FontFamily
        @Composable
        get() = FontFamily(
            Font(
                Res.font.Baloo2_Regular,
                FontWeight.Normal
            ),
            Font(
                Res.font.Baloo2_Medium,
                FontWeight.Medium
            ),
            Font(
                Res.font.Baloo2_Bold,
                FontWeight.Bold
            ),
        )
}

val AppTypography: Typography
    @Composable
    get() = Typography(
        displayLarge = TextStyle(
            fontFamily = AppFonts.Baloo2,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSizes.Title,
            color = PrimaryColor
        ),

        headlineLarge = TextStyle(
            fontFamily = AppFonts.Baloo2,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSizes.Subtitle,
            color = PrimaryColor
        ),

        titleLarge = TextStyle(
            fontFamily = AppFonts.Nunito,
            fontWeight = FontWeight.Bold,
            fontSize = FontSizes.Header,
            color = PrimaryColor
        ),

        bodyLarge = TextStyle(
            fontFamily = AppFonts.Nunito,
            fontWeight = FontWeight.Normal,
            fontSize = FontSizes.Subheader,
            color = TextBodyColor
        ),

        bodyMedium = TextStyle(
            fontFamily = AppFonts.Nunito,
            fontWeight = FontWeight.Normal,
            fontSize = FontSizes.Body,
            color = TextBodyColor
        ),

        bodySmall = TextStyle(
            fontFamily = AppFonts.Nunito,
            fontWeight = FontWeight.Light,
            fontSize = FontSizes.Emphasis,
            color = TextBodyColor
        ),

        labelLarge = TextStyle(
            fontFamily = AppFonts.Nunito,
            fontWeight = FontWeight.Bold,
            fontSize = FontSizes.Subheader,
            color = TextBodyColor
        ),
    )
