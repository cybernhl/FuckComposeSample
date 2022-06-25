package com.top.compose.sample.bean

class TResponse<T> {
     var errorCode: Int = 0
        get() = field
        set(value) {
            field = value
        }


     var errorMsg: String = ""
        get() = field
        set(value) {
            field = value
        }

     var data: T? = null
        get() = field
        set(value) {
            field = value
        }
}