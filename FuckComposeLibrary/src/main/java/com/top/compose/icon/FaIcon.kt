package com.top.compose.icon

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.top.compose.widget.R

@Composable
fun FaIcon(
    faIcon: FaIconType,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    tint: Color = Color.Unspecified
) {
    val scaleFactor = LocalConfiguration.current.fontScale

    val scaleIndependentFontSize =
        scaleIndependentFontSize(sizeDp = size, scaleFactor = scaleFactor)

    val faTextStyle = TextStyle(
        color = tint,
        fontFamily = getFontFamily(faIcon),
        fontSize = scaleIndependentFontSize
    )

    BasicText(
        text = faIcon.code.codePointToString(),
        modifier = modifier,
        style = faTextStyle
    )

}

private fun Int.codePointToString(): String = this.toChar().toString()


private fun getFontFamily(faIconType: FaIconType): FontFamily {
    return when (faIconType) {
        is FaIconType.BrandIcon -> FontFamily(Font(resId = R.font.fa_brands_400))
        is FaIconType.SolidIcon -> FontFamily(Font(resId = R.font.fa_solid_900))
        is FaIconType.RegularIcon -> FontFamily(Font(resId = R.font.fa_regular_400))
    }
}


private fun scaleIndependentFontSize(sizeDp: Dp, scaleFactor: Float): TextUnit {
    val materialIconOffset = 3.dp
    return ((sizeDp - materialIconOffset).value / scaleFactor).sp
}