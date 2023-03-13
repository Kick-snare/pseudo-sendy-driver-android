package com.uzun.pseudosendydriver.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

val localSendyTypography = staticCompositionLocalOf { sendyTypography }

@Composable
fun PseudoSendyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (false) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(
        localSendyTypography provides SendyTypography()
    ) {
        MaterialTheme(
            colors = colors,
            shapes = Shapes,
            content = content
        )
    }
}

object PseudoSendyTheme {
    val typography: SendyTypography
        @Composable
        get() = localSendyTypography.current
}