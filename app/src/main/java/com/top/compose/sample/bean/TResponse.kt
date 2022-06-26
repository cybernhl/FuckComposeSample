package com.top.compose.sample.bean


abstract class BaseResponse<T> {
    //抽象方法，用户的基类继承该类时，需要重写该方法
    abstract fun isSuccess(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String

}

data class TResponse<T>(val errorCode: Int, val errorMsg: String, val data: T) : BaseResponse<T>() {
    override fun isSuccess(): Boolean = errorCode == 0

    override fun getResponseData(): T = data

    override fun getResponseCode(): Int = errorCode

    override fun getResponseMsg(): String = errorMsg
}