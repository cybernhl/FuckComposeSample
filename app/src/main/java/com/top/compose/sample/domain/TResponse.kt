package com.top.compose.sample.domain


data class TResponse<T>(val errorCode: Int, val errorMsg: String, val data: T) : BaseResponse<T>() {
    override fun isSuccess(): Boolean = errorCode == 0

    override fun getResponseData(): T = data

    override fun getResponseCode(): Int = errorCode

    override fun getResponseMsg(): String = errorMsg

}