package com.top.react.activity

import android.os.Bundle
import com.top.react.*
import com.top.react.download.Downloader
import com.top.react.download.Request

class LoadReactActivity : LazyLoadReactActivity() {

    private var mReload: RootViewReload? = null

    private var mReactJsBundleFactory: ReactJsBundleFactory? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var options = intent.getSerializableExtra(ReactNativeOptions.KEY) as ReactNativeOptions?

        if (null == options) {
            options = ReactNativeOptions.default(this)
        }

        mReactJsBundleFactory = ReactJsBundleFactory(this, options)

        mReload = getReactInstanceManager()?.let {
            RootViewReload(this, it)
        }
        //mReactJsBundleFactory?.install("$cacheDir/index.android.bundle", "app")
        if (options.isAssets()) {
            reloadJSBundle(
                options.jsBundlePath,
                options.componentName
            )
            //hideLoading()
        } else {
            val downloader = Downloader(this)

            val request: Request = Request
                .Builder()
                .url(options.jsBundlePath)
                .build()

            downloader
                .request(request)
                .execute({
                    showLoading()
                }, {
                    reloadJSBundle(
                        it,
                        options.componentName
                    )
                    hideLoading()
                }, {
                    hideLoading()
                }, {

                })
        }
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


    private fun getModuleName(moduleName: String?): String {
        var mainComponentName: String? = null
        if (null == moduleName) {
            mainComponentName = getMainComponentName()
        }
        if (null == mainComponentName) {
            mainComponentName = DEFAULT_COMPONENT_NAME
        }
        return mainComponentName
    }


}