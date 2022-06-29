package com.top.compose.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

public abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    private lateinit var vm: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}