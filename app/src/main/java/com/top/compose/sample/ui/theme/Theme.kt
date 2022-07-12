package com.top.compose.sample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val TestColorPalette = lightColors(
    primary = Color.Red,
    primaryVariant = Color.Black,
    secondary = Color.Green,
    secondaryVariant=Color.LightGray,
    background = Color.Blue,
    surface = Color.Gray,
    onPrimary = Color.Cyan,
    onSecondary = Color.DarkGray,
    onBackground = Color.Black,
    onSurface = Color.Magenta,
    onError=Color.White,
    error = Color.Yellow
)


private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant=Teal200,
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
    secondaryVariant=Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red
)

private val DartRedPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant=Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red
)


private val LightRedPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant=Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red
)


enum class ColorPallet(val color: Color) {
    RED(Color.Red), GREEN(Color.Green), TEST(Color.Yellow)
}

@Composable
fun FuckComposeSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorPallet: ColorPallet = ColorPallet.GREEN,
    content: @Composable () -> Unit
) {
    val colors = when (colorPallet) {
        ColorPallet.GREEN -> if (darkTheme) DarkColorPalette else LightColorPalette
        ColorPallet.RED -> if (darkTheme) DartRedPalette else LightRedPalette
        ColorPallet.TEST -> TestColorPalette

    }
    MaterialTheme(
        colors = colors,  //颜色
        typography = Typography,  //排版
        shapes = Shapes,  //形状
        content = content
    )
}