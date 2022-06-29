package com.top.compose.sample.business.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.top.compose.icon.FaIcons
import com.top.compose.icon.FaIcons.Box
import com.top.compose.sample.lottie.LottieSplashAnimation
import com.top.compose.widget.TopAppBarCenter
import com.top.fix.sample.business.ConstantRoute
import kotlinx.coroutines.delay

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
                    .align(alignment = Alignment.Center)
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








