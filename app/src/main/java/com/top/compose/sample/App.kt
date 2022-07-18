package com.top.compose.sample

import android.app.Application
import com.tencent.mmkv.MMKV
import com.top.react.application.ReactNativeApplication
import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp(MultiDexApplication::class)
@HiltAndroidApp
class App : ReactNativeApplication() {

    override fun onCreate() {
        super.onCreate()

        MMKV.initialize(this)

    }
}