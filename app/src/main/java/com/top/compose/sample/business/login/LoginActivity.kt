package com.top.compose.sample.business.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint


fun launchDetailsActivity(context: Context) {
    val intent = Intent(context, LoginActivity::class.java)

    context.startActivity(intent)
}

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LoginScreen(onClick = { finish() })
        }
    }

}