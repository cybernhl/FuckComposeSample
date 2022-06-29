package com.top.compose.sample.business.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.top.compose.sample.ui.lottie.LottieSplashAnimation
import com.top.compose.widget.TopAppBarCenter
import com.top.fix.sample.business.ConstantRoute

@Composable
fun SplashScreen(
    navController: NavController,
) {
    TopAppBarCenter(title = {}, backgroundColor = Color.White, isImmersive = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            LottieSplashAnimation(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .align(alignment = Alignment.TopCenter)
                    .padding(top = 60.dp)
            ) {
                navController.popBackStack()
                navController.navigate(ConstantRoute.MAIN_SCREEN)
            }


            Text(
                text = " Kotlin Android Jetpack Compose",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 30.dp)
            )

        }
    }

}








