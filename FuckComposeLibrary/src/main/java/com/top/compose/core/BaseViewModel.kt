package com.top.compose.core

import androidx.lifecycle.MutableLiveData

open class BaseViewModel : LifecycleViewModel() {


//    fun <T> request(
//        block: () -> String,
//        success: (T) -> Unit,
//        error: (Throwable) -> Unit = {},
//        isShowDialog: Boolean = false,
//        loadingMessage: String = "请求服务器中..."
//    ): Job {
//
//        return viewModelScope.launch {
//
//
//        }
//    }

    val loading by lazy { MutableLiveData<Boolean>() }

//    val loading: Loading by lazy { Loading() }
//
//    inner class Loading {
//        val showLoading by lazy { MutableLiveData<String>() }
//        val dismissLoading by lazy { MutableLiveData<Boolean>() }
//    }
}