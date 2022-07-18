package com.top.react.navigation

import android.content.Context
import android.util.Log
import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

class NavigateModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule() {

    private val context: Context = reactContext

    override fun getName(): String {
        return "Navigate"

    }


    /**
     * 关闭当前页面
     */
    @ReactMethod
    fun close() {
        val activity = currentActivity
        activity?.finish()
    }

    /**
     * react native 发送消息到jsbundle前端
     *
     * @param eventName
     * @param params
     */
    private fun event(eventName: String, params: WritableMap?) {
        var params = params
        val context = this.reactApplicationContext ?: return
        if (null == params) {
            params = Arguments.createMap()
        }
        Log.d(this.javaClass.simpleName, String.format("event -> name: %s", eventName))
        context.getJSModule(RCTDeviceEventEmitter::class.java).emit(eventName, params)
    }
}