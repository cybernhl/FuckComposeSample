package com.top.compose.sample.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.top.compose.icon.FaIcon
import com.top.compose.icon.FaIconType


@Composable
fun RotateIcon(
    state: Boolean = true,
    faIcon: FaIconType,
    angle: Float,
    duration: Int,
    modifier: Modifier = Modifier
) {
    FaIcon(
        faIcon = faIcon,
        tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
        size = 20.dp,
        modifier = modifier
            .padding(2.dp)
            .graphicsLayer(
                rotationZ = animateFloatAsState(if (state) 0f else angle, tween(duration))
                    .value
            )
    )
}


@Composable
fun EmptyScreen(msg: String) {
    Scaffold {

        Column(modifier = Modifier.padding(it)) {
            Text(text = msg, textAlign = TextAlign.Center)
        }
    }

}
