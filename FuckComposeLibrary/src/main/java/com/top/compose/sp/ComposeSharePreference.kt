package com.top.compose.sp

import android.content.Context
import android.content.SharedPreferences

/**
 * @param: null
 * @description: ComposeSharePreference具体实现
 * @return:
 * @author: leo
 * @date: 2022/6/17 14:57
 */
class ComposeSharePreference(private var context: Context) : ComposeSharePreferenceInterface {

    private var preference: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)


    /**
     * @param:
     *      @key:键
     *      @defaultValue:值
     * @description: 获取Sp指定的键值
     * @return:
     * @author: leo
     * @date: 2022/6/17 11:12
     */
    private fun <T> getPreferenceValue(key: String, defaultValue: T): T = with(preference) {
        val res: Any = when (defaultValue) {
            is Long -> getLong(key, defaultValue)
            is String -> this.getString(key, defaultValue)!!
            is Int -> getInt(key, defaultValue)
            is Boolean -> getBoolean(key, defaultValue)
            is Float -> getFloat(key, defaultValue)
            else -> throw IllegalArgumentException("ComposeSharePreference only support type for long、String、Int、Boolean、Float")
        }
        return res as T
    }

    private fun <T> putPreferenceValue(name: String, value: T) = with(preference.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is Int -> putInt(name, value)
            is String -> putString(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("ComposeSharePreference only support type for long、String、Int、Boolean、Float")
        }.apply()
    }


    override fun <T> put(key: String, data: T) {
        putPreferenceValue(key, data)
    }

    override fun <T> get(key: String, default: T): T {
        return getPreferenceValue(key, default)
    }


}


