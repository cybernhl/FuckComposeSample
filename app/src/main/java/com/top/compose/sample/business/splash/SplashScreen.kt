package com.top.compose.sample.business.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.top.compose.sample.lottie.LottieSplashAnimation
import com.top.fix.sample.business.ConstantRoute

@Composable
fun SplashScreen(
    navController: NavController,
) {

//    LaunchedEffect(key1 = true) {
//        delay(30000)
//        navController.popBackStack()
//        if (isLogin) {
//            navController.navigate(ConstantRoute.MAIN_SCREEN)
//        } else {
//            navController.navigate(ConstantRoute.MAIN_SCREEN)
//        }
//    }

    val current = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LottieSplashAnimation {
            //Toast.makeText(current,"UUUUU",Toast.LENGTH_SHORT).show()
            //navController.popBackStack()
            navController.navigate(ConstantRoute.MAIN_SCREEN)
        }
    }
}








