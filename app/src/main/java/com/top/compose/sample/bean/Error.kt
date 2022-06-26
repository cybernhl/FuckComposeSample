package com.top.compose.sample.bean

enum class Error(private val code: Int, private val err: String) {


    UNKNOWN(1000, "请求失败，请稍后再试");


    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}