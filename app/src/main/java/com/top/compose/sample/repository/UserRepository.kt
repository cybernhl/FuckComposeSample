package com.top.compose.sample.repository

import com.top.compose.sample.bean.AppException
import com.top.compose.sample.bean.User
import com.top.compose.sample.domain.WanAndroidClient
import javax.inject.Inject
import javax.inject.Singleton

class UserRepository @Inject constructor() {

    suspend fun login(userName: String, userPwd: String): User {
        val login = WanAndroidClient.getApiUrl.login(userName, userPwd)
        if (login.isSuccess()) {
            return login.data
        } else {
            throw AppException(login.errorCode, login.errorMsg)
        }
    }
}