package com.top.react

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.View
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.bridge.CatalystInstance
import com.facebook.react.bridge.JSBundleLoader
import java.util.*


class RootViewReload(
    private val mActivity: Activity,
    private val mReactInstanceManager: ReactInstanceManager
) {

    @Throws(IllegalAccessException::class)
    fun setJSBundle(latestJSBundleFile: String) {
        try {
            val latestJSBundleLoader: JSBundleLoader = if (latestJSBundleFile.lowercase(Locale.getDefault())
                    .startsWith(ASSETS_BUNDLE_PREFIX)
            ) {
                JSBundleLoader.createAssetLoader(mActivity, latestJSBundleFile, false)
            } else {
                JSBundleLoader.createFileLoader(latestJSBundleFile)
            }
            val bundleLoaderField =
                mReactInstanceManager.javaClass.getDeclaredField("mBundleLoader")
            bundleLoaderField.isAccessible = true
            bundleLoaderField[mReactInstanceManager] = latestJSBundleLoader
        } catch (e: Exception) {
            throw IllegalAccessException("Could not setJSBundle")
        }
    }

    // #3) Get the context creation method and fire it on the UI thread (which RN enforces)
    fun reload() {
        Handler(Looper.getMainLooper()).post {
            try {
                // We don't need to resetReactRootViews anymore
                // due the issue https://github.com/facebook/react-native/issues/14533
                // has been fixed in RN 0.46.0
//                    ReactInstanceManager instanceManager = getReactInstanceManager();
                resetReactRootViews(mReactInstanceManager)
                mReactInstanceManager.recreateReactContextInBackground()
            } catch (e: Exception) {
                activityReload(mActivity)
                // The recreation method threw an unknown exception
                // so just simply fallback to restarting the Activity (if it exists)
            }
        }
    }

    private fun activityReload(activity: Activity) {
        activity.runOnUiThread { activity.recreate() }
    }

    // This workaround has been implemented in order to fix https://github.com/facebook/react-native/issues/14533
    // resetReactRootViews allows to call recreateReactContextInBackground without any exceptions
    // This fix also relates to https://github.com/microsoft/react-native-code-push/issues/878
    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    private fun resetReactRootViews(reactInstanceManager: ReactInstanceManager) {
        val mAttachedRootViewsField =
            reactInstanceManager.javaClass.getDeclaredField("mAttachedRootViews")
        mAttachedRootViewsField.isAccessible = true
        val mAttachedRootViews =
            mAttachedRootViewsField[reactInstanceManager] as List<ReactRootView>
        for (reactRootView in mAttachedRootViews) {
            reactRootView.removeAllViews()
            reactRootView.id = View.NO_ID
        }
        mAttachedRootViewsField[reactInstanceManager] = mAttachedRootViews
    }

    fun loadScriptFromAsset(assetName: String, loadSynchronously: Boolean) {
        val instance = mReactInstanceManager.currentReactContext!!.catalystInstance
        val source =
            if (assetName.startsWith(ASSETS_BUNDLE_PREFIX)) assetName else ASSETS_BUNDLE_PREFIX + assetName
        instance.loadScriptFromAssets(mActivity.assets, source, loadSynchronously)
    }

    fun loadScriptFromFile(fileName: String?, sourceUrl: String?, loadSynchronously: Boolean) {
        val instance = mReactInstanceManager.currentReactContext!!.catalystInstance
        instance.loadScriptFromFile(fileName, sourceUrl, loadSynchronously)
        //        try {
//            Method method = CatalystInstanceImpl.class.getDeclaredMethod("loadScriptFromFile", String.class, String.class, boolean.class);
//            method.setAccessible(true);
//            method.invoke(instance, fileName, sourceUrl, loadSynchronously);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private fun catalystInstance(manager: ReactInstanceManager?): CatalystInstance? {
        if (null == manager) return null
        val context = manager.currentReactContext ?: return null
        return context.catalystInstance
    }
}