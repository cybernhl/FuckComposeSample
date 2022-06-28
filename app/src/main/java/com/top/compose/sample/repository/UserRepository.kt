package com.top.compose.sample.repository

import com.top.compose.sample.bean.AppException
import com.top.compose.sample.bean.TResponse
import com.top.compose.sample.bean.User
import com.top.compose.sample.domain.WanAndroidClient
import javax.inject.Inject

class UserRepository @Inject constructor() {

    suspend fun login(userName: String, userPwd: String): TResponse<User> {
        val login = WanAndroidClient.getApiUrl.login(userName, userPwd)
        if (login.isSuccess()) {
            return login
        } else {
            throw AppException(login.errorCode, login.errorMsg)
        }
    }
}