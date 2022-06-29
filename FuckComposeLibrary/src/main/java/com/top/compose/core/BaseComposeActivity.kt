package com.top.compose.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

public abstract class BaseComposeActivity<VM : BaseViewModel> : ComponentActivity() {

    private lateinit var vm: VM



}