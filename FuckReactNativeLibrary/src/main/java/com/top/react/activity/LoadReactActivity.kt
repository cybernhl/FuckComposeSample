package com.top.react.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import com.top.react.DEFAULT_COMPONENT_NAME
import com.top.react.Page
import com.top.react.ReactJsBundleFactory
import com.top.react.RootViewReload

class LoadReactActivity : LazyLoadReactActivity() {

    private var mReload: RootViewReload? = null
    private var mReactJsBundleFactory: ReactJsBundleFactory? = null

    private var mPage: Page? = null

    var bundleName: String? = null

    var initRouteUrl: String? = null

    var url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mReactJsBundleFactory = ReactJsBundleFactory(this, cacheDir.absolutePath)
        mReload = getReactInstanceManager()?.let { RootViewReload(this, it) }
        //mPage = Page().url(url).name(bundleName).extras("initRouteUrl", initRouteUrl)

        //mReactJsBundleFactory?.install("assets:///android_asset/","index.android.bundle")
        reloadJSBundle("assets:///android_asset/", "index")
    }

    override fun getMainComponentName(): String? {
        return null
    }


    private fun reloadJSBundle(jsBundleFile: String, componentName: String) {
        try {
            mReload!!.setJSBundle(jsBundleFile)
            loadApp(getModuleName(componentName))
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }


    private fun getModuleName(moduleName: String?): String? {
        var mainComponentName: String? = null
        if (null == moduleName) {
            mainComponentName = getMainComponentName()
        }
        if (null == mainComponentName) {
            mainComponentName = DEFAULT_COMPONENT_NAME
        }
        return mainComponentName
    }


    private fun showLoading() {
        //runOnUiThread { if (null != loadingDialog) loadingDialog.show() }
    }

    private fun dismissLoading() {
        //runOnUiThread { if (null != loadingDialog) loadingDialog.dismiss() }
    }

}