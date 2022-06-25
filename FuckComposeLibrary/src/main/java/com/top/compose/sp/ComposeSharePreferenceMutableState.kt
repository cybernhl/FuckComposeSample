package com.top.compose.sp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.reflect.KProperty

/**
 * @param: null
 * @description: 保存值的状态，动态修改值，并更新ui
 * @return:
 * @author: leo
 * @date: 2022/6/17 14:57
 */
class ComposeSharePreferenceMutableState<T>(
    private val composeSharePreferenceInterface: ComposeSharePreferenceInterface,
    private val key: String,
    value: T,
    private val autoSave: Boolean = true
) {

    companion object {
        val typeConverters: MutableMap<Class<*>, (Any) -> Any> = mutableMapOf()
    }


    private var state: MutableState<T> = mutableStateOf(value)



    operator fun setValue(thisObj: Any?, property: KProperty<*>, value: T) {
        if (autoSave && this.state.value != value) saveData(value)
        this.state.value = value
    }

    operator fun getValue(thisObj: Any?, property: KProperty<*>): T {
        return this.state.value
    }

    private fun saveData(value: T) {
        value ?: return
        val typeConverter = typeConverters[value!!::class.java]
        if (typeConverter != null) {
            composeSharePreferenceInterface.put(key, typeConverter(value))
        } else {
            composeSharePreferenceInterface.put(key, value)
        }
    }


}
