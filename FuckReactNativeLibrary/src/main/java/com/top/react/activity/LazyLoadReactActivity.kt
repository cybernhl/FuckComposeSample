package com.top.react.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.ReactApplication
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.top.react.delegate.LoadReactDelegate

abstract class LazyLoadReactActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler {


    private var mReactDelegate: LoadReactDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mReactDelegate = createReactActivityDelegate()
    }

    override fun onPause() {
        super.onPause()
        mReactDelegate?.onHostPause()
    }

    override fun onResume() {
        super.onResume()
        mReactDelegate?.onHostResume()

    }

    override fun onDestroy() {
        super.onDestroy()
        mReactDelegate?.onHostDestroy()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mReactDelegate?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        return mReactDelegate?.onKeyLongPress(keyCode, event) == true || super.onKeyLongPress(
            keyCode,
            event
        )
    }

    override fun onBackPressed() {
        if (mReactDelegate?.onBackPressed() != true){
            super.onBackPressed()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mReactDelegate?.onNewIntent(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mReactDelegate?.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun onWindowFocusChanged( hasFocus:Boolean) {
        super.onWindowFocusChanged(hasFocus)
        mReactDelegate?.onWindowFocusChanged(hasFocus)
    }


    protected fun createReactActivityDelegate(): LoadReactDelegate {
        val reactNativeHost = (application as ReactApplication).reactNativeHost
        return LoadReactDelegate(this, reactNativeHost)
    }

    protected fun getReactInstanceManager(): ReactInstanceManager? {
        return mReactDelegate?.getReactInstanceManager()
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()

    }


    protected abstract fun getMainComponentName(): String?


    protected fun loadApp(appKey: String?) {
        loadApp(appKey, intent.extras)
    }

    protected fun loadApp(appKey: String?, launchOptions: Bundle?) {
        mReactDelegate?.loadApp(appKey, launchOptions)
    }

    protected fun getReactDelegate(): LoadReactDelegate {
        return mReactDelegate!!
    }

    protected fun getReactNativeHost(): ReactNativeHost? {
        return mReactDelegate!!.getReactNativeHost()
    }



}