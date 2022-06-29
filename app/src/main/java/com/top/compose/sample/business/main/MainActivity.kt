package com.top.compose.sample.business.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.top.compose.sample.business.login.LoginScreen
import com.top.compose.sample.business.splash.SplashScreen
import com.top.compose.sample.ui.theme.AppThemeState
import com.top.compose.sample.ui.theme.SuperHotFixSampleTheme
import com.top.fix.sample.business.ConstantRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置沉浸式状态栏
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SuperHotFixSampleTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val rememberNavController = rememberNavController()
    NavHost(navController = rememberNavController, startDestination = ConstantRoute.SPLASH_SCREEN) {
        composable(ConstantRoute.SPLASH_SCREEN) {
            SplashScreen(navController = rememberNavController)
        }
        composable(ConstantRoute.LOGIN_SCREEN) {
            LoginScreen(rememberNavController)
        }
        composable(ConstantRoute.MAIN_SCREEN) {
            MainScreen(appThemeState = AppThemeState(false), Modifier)
        }
    }
}