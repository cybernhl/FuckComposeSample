package com.top.compose.sp

/**
 * @description: ComposeSharePreferenceInterface接口
 * @return:
 * @author: leo
 * @date: 2022/6/17 14:56
 */
interface ComposeSharePreferenceInterface {
    fun <T> put(key: String, data: T)
    fun <T> get(key: String, default: T): T
}