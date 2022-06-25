package com.top.compose.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextImage(painter: Painter, text: String) {
    Column {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(48.dp)
                .clip(shape = RoundedCornerShape(100)),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Text(
            text,
            fontSize = 12.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun TextImage(@DrawableRes id: Int, text: String) {
    Column {
        Image(
            painter = painterResource(id),
            contentDescription = "",
            modifier = Modifier
                .size(48.dp)
                .clip(shape = RoundedCornerShape(100)),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Text(
            text,
            fontSize = 12.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
    }
}