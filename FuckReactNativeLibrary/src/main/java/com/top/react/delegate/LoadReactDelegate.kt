package com.top.react.delegate

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import com.facebook.infer.annotation.Assertions
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import com.facebook.react.bridge.Callback
import com.facebook.react.devsupport.DoubleTapReloadRecognizer
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.modules.core.PermissionListener


class LoadReactDelegate(activity: Activity, reactNativeHost: ReactNativeHost) {

    private var mActivity: Activity? = activity

    private var mReactRootView: ReactRootView? = null

    private var mReactNativeHost: ReactNativeHost? = reactNativeHost

    private var mDoubleTapReloadRecognizer: DoubleTapReloadRecognizer? = null

    private var mPermissionListener: PermissionListener? = null

    private var mPermissionsCallback: Callback? = null


    init {
        mDoubleTapReloadRecognizer = DoubleTapReloadRecognizer()
    }

    fun onHostResume() {
        if (getReactNativeHost()?.hasInstance() == true) {
            if (mActivity is DefaultHardwareBackBtnHandler) {
                getReactNativeHost()?.reactInstanceManager?.onHostResume(
                    mActivity,
                    mActivity as DefaultHardwareBackBtnHandler?
                )
            } else {
                throw ClassCastException("Host Activity does not implement DefaultHardwareBackBtnHandler")
            }
            if (null != mPermissionsCallback) {
                mPermissionsCallback?.invoke()
                mPermissionsCallback = null
            }
        }
    }


    fun onHostPause() {
        if (getReactNativeHost()?.hasInstance() == true) {
            getReactNativeHost()?.reactInstanceManager?.onHostPause(mActivity)
        }
    }

    fun onHostDestroy() {
        if (null != mReactRootView) {
            mReactRootView?.unmountReactApplication()
            mReactRootView = null
        }
        if (getReactNativeHost()?.hasInstance() == true) {
            getReactNativeHost()?.reactInstanceManager?.onHostDestroy(mActivity)
        }
    }

    fun onBackPressed(): Boolean {
        if (getReactNativeHost()?.hasInstance() == true) {
            getReactNativeHost()?.reactInstanceManager?.onBackPressed()
            return true
        }
        return false
    }


    fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        if (getReactNativeHost()?.hasInstance() == true
            && getReactNativeHost()?.useDeveloperSupport == true
            && keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD
        ) {
            getReactNativeHost()?.reactInstanceManager?.showDevOptionsDialog()
            return true
        }
        return false
    }


    fun onWindowFocusChanged(hasFocus: Boolean) {
        if (getReactNativeHost()?.hasInstance() == true) {
            getReactNativeHost()?.reactInstanceManager?.onWindowFocusChange(hasFocus)
        }
    }

    fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        shouldForwardToReactInstance: Boolean
    ) {
        if (getReactNativeHost()?.hasInstance() == true && shouldForwardToReactInstance) {
            getReactNativeHost()?.reactInstanceManager
                ?.onActivityResult(mActivity, requestCode, resultCode, data)
        }
    }


    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        this.onActivityResult(requestCode, resultCode, data, true)
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (getReactNativeHost()!!.hasInstance()
            && getReactNativeHost()!!.useDeveloperSupport
            && keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD
        ) {
            event.startTracking()
            return true
        }
        return false
    }

    fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return shouldShowDevMenuOrReload(keyCode, event)
    }

    fun shouldShowDevMenuOrReload(keyCode: Int, event: KeyEvent?): Boolean {
        if (getReactNativeHost()!!.hasInstance() && getReactNativeHost()!!.useDeveloperSupport) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                getReactNativeHost()!!.reactInstanceManager.showDevOptionsDialog()
                return true
            }

            val didDoubleTapR: Boolean =
                Assertions.assertNotNull<DoubleTapReloadRecognizer>(mDoubleTapReloadRecognizer)
                    .didDoubleTapR(keyCode, mActivity!!.currentFocus)

            if (didDoubleTapR) {
                getReactNativeHost()!!.reactInstanceManager.devSupportManager.handleReloadJS()
                return true
            }
        }
        return false
    }


    fun onNewIntent(intent: Intent?): Boolean {
        if (getReactNativeHost()!!.hasInstance()) {
            getReactNativeHost()!!.reactInstanceManager.onNewIntent(intent)
            return true
        }
        return false
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions(
        permissions: Array<String?>?,
        requestCode: Int,
        listener: PermissionListener
    ) {
        mPermissionListener = listener
        mActivity!!.requestPermissions(permissions!!, requestCode)
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray?
    ) {
        mPermissionsCallback = Callback { args: Array<Any?>? ->
            if (null != mPermissionListener && mPermissionListener!!.onRequestPermissionsResult(
                    requestCode,
                    permissions,
                    grantResults
                )
            ) {
                mPermissionListener = null
            }
        }
    }


    fun getReactNativeHost(): ReactNativeHost? {
        return mReactNativeHost
    }

    fun getReactRootView(): ReactRootView? {
        return mReactRootView
    }

    protected fun createRootView(): ReactRootView {
        return ReactRootView(mActivity)
    }

    fun getReactInstanceManager(): ReactInstanceManager? {
        return getReactNativeHost()?.reactInstanceManager
    }

    fun loadApp(appKey: String?, launchOptions: Bundle?) {
        check(null == mReactRootView) { "Cannot loadApp while app is already running." }
        // react native 的偶现BUG
        // https://github.com/facebook/react-native/issues/28461
        // react native 的偶现BUG
        // https://github.com/facebook/react-native/issues/28461
        launchOptions?.remove("profile")
        mReactRootView = createRootView()
        if (null != mActivity) mActivity!!.setContentView(mReactRootView)
        mReactRootView!!.startReactApplication(
            getReactNativeHost()!!.reactInstanceManager,
            appKey,
            launchOptions
        )
    }

}