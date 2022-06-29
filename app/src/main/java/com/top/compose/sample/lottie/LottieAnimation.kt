package com.top.compose.sample.lottie

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.top.compose.sample.R
import com.top.fix.sample.business.ConstantRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay


@Composable
fun LottieSplashAnimation(modifier: Modifier = Modifier, end: () -> Unit) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.kotlin_android))
    val progress by animateLottieCompositionAsState(lottieComposition)


    LaunchedEffect(key1 = progress) {
        if (progress == 1f) {
            end()
        }
    }

    LottieAnimation(
        composition = lottieComposition,
        progress = {
            progress
        },
        modifier = modifier
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
            .height(250.dp),
        restartOnPlay = true
    )
}


@Composable
fun LottieAnimationImage(@androidx.annotation.RawRes resId: Int, modifier: Modifier = Modifier) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))

    LottieAnimation(
        composition = lottieComposition,
        modifier = modifier
            .clip(shape = RoundedCornerShape(100)),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}
