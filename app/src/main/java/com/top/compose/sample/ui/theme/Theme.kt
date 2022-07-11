package com.top.compose.sample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red
    )

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red
    )

enum class ColorPallet {
    PURPLE, GREEN, ORANGE, BLUE
}

@Composable
fun FuckComposeSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorPallet: ColorPallet = ColorPallet.GREEN,
    content: @Composable () -> Unit
) {

    val colors = when (colorPallet) {
        ColorPallet.GREEN -> if (darkTheme) DarkColorPalette else LightColorPalette
        ColorPallet.PURPLE -> if (darkTheme) DarkColorPalette else LightColorPalette
        ColorPallet.ORANGE -> if (darkTheme) DarkColorPalette else LightColorPalette
        ColorPallet.BLUE -> if (darkTheme) DarkColorPalette else LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}