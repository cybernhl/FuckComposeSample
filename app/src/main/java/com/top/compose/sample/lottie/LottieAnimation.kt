package com.top.compose.sample.lottie

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.top.compose.sample.R


@Composable
fun LottieSplashAnimation(end: () -> Unit) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.kotlin_android))
    val progress by animateLottieCompositionAsState(lottieComposition)

    if (progress == 1f) {
        end()
    }
    LottieAnimation(
        composition = lottieComposition,
        progress = {
            progress
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}


@Composable
fun LottieLoginAnimation() {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login))

    LottieAnimation(
        composition = lottieComposition,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}