package com.top.compose.sample.repository

import com.top.compose.sample.bean.TResponse
import com.top.compose.sample.bean.User
import com.top.compose.sample.domain.WanAndroidClient

class APIRepository {

    companion object {
        suspend fun login(userName: String, userPwd: String): TResponse<User> {
            return WanAndroidClient.getApiUrl.login(userName, userPwd)
        }
    }
}