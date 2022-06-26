package com.top.compose.sample.business.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.top.compose.sample.R
import com.top.compose.sp.rememberSharePreferenceState
import com.top.fix.sample.business.ConstantRoute
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
) {

    val isLogin by rememberSharePreferenceState(
        key = "isLogin",
        defaultValue = false
    )

    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack()
        if (isLogin) {
            navController.navigate(ConstantRoute.MAIN_SCREEN)
        } else {
            navController.navigate(ConstantRoute.MAIN_SCREEN)
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(modifier = Modifier.align(Alignment.Center)) {
            var expanded by remember {
                mutableStateOf(false)
            }
            Column(modifier = Modifier.clickable { expanded = !expanded }) {

                Image(
                    painterResource(R.drawable.ic_logo), contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                )
                AnimatedVisibility(expanded) {
                    Text(text = "Jetpack Compose", style = MaterialTheme.typography.h6)
                }
            }
        }
    }
}








