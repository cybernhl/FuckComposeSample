package com.top.compose.sample

import android.app.Application
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp(MultiDexApplication::class)
@HiltAndroidApp
class App :Application() {

    override fun onCreate() {
        super.onCreate()

        MMKV.initialize(this)

    }
}