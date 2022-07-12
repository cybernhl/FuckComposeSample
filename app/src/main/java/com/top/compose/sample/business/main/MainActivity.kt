package com.top.compose.sample.business.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.LottieProperty.COLOR
import com.top.compose.sample.business.login.LoginScreen
import com.top.compose.sample.business.login.RegisterScreen
import com.top.compose.sample.business.splash.SplashScreen
import com.top.compose.sample.ui.theme.AppThemeState
import com.top.compose.sample.ui.theme.FuckComposeSampleTheme
import com.top.fix.sample.business.ConstantRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置沉浸式状态栏
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val appTheme = remember { mutableStateOf(AppThemeState()) }

            FuckComposeSampleTheme(colorPallet = appTheme.value.pallet) {
                Navigation(appTheme)
            }
        }
    }
}

@Composable
fun Navigation(appThemeState: MutableState<AppThemeState>) {
    val rememberNavController = rememberNavController()

    NavHost(navController = rememberNavController, startDestination = ConstantRoute.SPLASH_SCREEN) {
        composable(ConstantRoute.SPLASH_SCREEN) {
            SplashScreen(navController = rememberNavController)
        }

        composable(ConstantRoute.MAIN_SCREEN) {
            MainScreen(
                appThemeState = appThemeState,
                modifier = Modifier,
                onNavigateTo = {
                    rememberNavController.navigate(it)
                }
            )
        }
        composable(ConstantRoute.LOGIN_SCREEN) {
            LoginScreen(navController = rememberNavController)
        }

        composable(ConstantRoute.REGISTER_SCREEN) {
            RegisterScreen(navController = rememberNavController)
        }


    }
}