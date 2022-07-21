package com.top.react

import android.content.Context
import java.io.File
import java.io.Serializable

class ReactNativeOptions : Serializable {


    //jsBundle的源，可能是Assets、也可能是url下载地址
    lateinit var jsBundlePath: String

    //jsBundle要安装的路径
    lateinit var jsBundleInstallDir: String

    //jsBundle的入口组件名
    lateinit var componentName: String


    fun isAssets(): Boolean {
        return jsBundlePath.startsWith("assets://")
    }

    fun isZip(): Boolean {
        return jsBundlePath.endsWith(".zip")
    }

    companion object {
        const val KEY = "ReactNativeOptions"
        fun default(context: Context): ReactNativeOptions {
            val options = ReactNativeOptions()
            options.jsBundlePath = "https://oss.suning.com/sffe/sffe/aries/69/20220701_150537.zip"
            //options.jsBundlePath = "assets://index_v1.android.bundle"
            //options.jsBundlePath = "assets://index_v2.android.bundle"
            //options.jsBundlePath = "assets://index_v3.android.bundle"
            options.jsBundleInstallDir = context.cacheDir.absolutePath
            options.componentName = "AwesomeProject"
            return options
        }
    }


}