package com.top.react.application

import android.app.Application
import com.facebook.common.logging.FLog
import com.facebook.react.BuildConfig
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
import com.facebook.soloader.SoLoader
import com.top.react.navigation.NavigatePackage

abstract class ReactNativeApplication : Application(), ReactApplication {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this,  /* native exopackage */false)

    }

    override fun getReactNativeHost(): ReactNativeHost {
        return mReactNativeHost
    }


    private val mReactNativeHost: ReactNativeHost = object : ReactNativeHost(this) {
        override fun getUseDeveloperSupport(): Boolean {
            return BuildConfig.DEBUG
        }

        override fun getPackages(): List<ReactPackage> {
            return listOf(
                MainReactPackage(),
                NavigatePackage()
            )
        }

        override fun getJSMainModuleName(): String {
            return "index"
        }
    }


}