package com.top.react.download

import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap

class DownloadProcess constructor(private val mTotalBytes: Long, private val mReceivedBytes: Long) {


    public fun isComplete(): Boolean {
        return mTotalBytes == mReceivedBytes
    }

    fun createWritableMap(): WritableMap {
        val map: WritableMap = WritableNativeMap()
        if (mTotalBytes < Int.MAX_VALUE) {
            map.putInt("totalBytes", mTotalBytes.toInt())
            map.putInt("receivedBytes", mReceivedBytes.toInt())
        } else {
            map.putDouble("totalBytes", mTotalBytes.toDouble())
            map.putDouble("receivedBytes", mReceivedBytes.toDouble())
        }
        return map
    }

}