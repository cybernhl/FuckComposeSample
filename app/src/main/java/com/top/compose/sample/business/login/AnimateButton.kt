package com.top.compose.sample.business.login

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


enum class AnimateButtonState {
    Normal,
    Pressed
}

@Composable
fun AnimateButton(emailText: MutableState<String>, passwordText: MutableState<String>) {

    val buttonState = remember {
        mutableStateOf(AnimateButtonState.Normal)
    }


    val updateTransition = updateTransition(targetState = buttonState, label = "")
    val duration = 600

    val buttonBackgroundColor: Color by updateTransition.animateColor(transitionSpec = {
        tween(
            duration
        )
    }, label = "ssss") {
        when (it.value) {
            AnimateButtonState.Normal -> Color.Gray
            AnimateButtonState.Pressed -> Color.Blue
        }
    }


    val buttonWidth: Dp by updateTransition.animateDp(
        transitionSpec = { tween(duration) },
        label = "sss"
    ) {
        when (it.value) {
            AnimateButtonState.Normal -> 280.dp
            AnimateButtonState.Pressed -> 60.dp
        }
    }

    val buttonShape: Dp by updateTransition.animateDp(
        transitionSpec = { tween(duration) },
        label = "ss"
    ) {
        when (it.value) {
            AnimateButtonState.Normal -> 4.dp
            AnimateButtonState.Pressed -> 60.dp
        }
    }

    Button(
        modifier = Modifier
            .height(46.dp)
            .width(buttonWidth),
        enabled = emailText.value.isNotBlank() && passwordText.value.isNotBlank(),
        onClick = {
            buttonState.value = if (buttonState.value == AnimateButtonState.Normal) {
                AnimateButtonState.Pressed
            } else {
                AnimateButtonState.Normal
            }

        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonBackgroundColor,
            disabledBackgroundColor = Color.LightGray.copy(0.5f)
        ),
        shape = RoundedCornerShape(buttonShape)
    ) {
        if (buttonState.value == AnimateButtonState.Normal) {
            Text(text = "登录", color = Color.White)
        } else {
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                modifier = Modifier.size(24.dp, 24.dp)
            )
        }
    }


}