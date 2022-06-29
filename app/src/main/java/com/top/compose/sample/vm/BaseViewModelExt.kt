package com.top.compose.sample.vm

import androidx.lifecycle.viewModelScope
import com.top.compose.core.BaseViewModel
import com.top.compose.sample.bean.TResponse
import com.top.compose.sample.bean.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

fun <T> BaseViewModel.request(
    block: () -> TResponse<T>,
    success: (T) -> Unit,
    error: (Throwable) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求服务器中..."
): Job {
    return viewModelScope.launch {

        runCatching {
            block()
        }.onSuccess {

        }.onFailure {

        }
    }
}

fun <T> BaseViewModel.flow(
    block: () -> String,
    success: (T) -> Unit,
    error: (Throwable) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求服务器中..."
): Flow<String> {

    return flow {

    }
}