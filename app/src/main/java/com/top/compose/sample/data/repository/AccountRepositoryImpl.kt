package com.top.compose.sample.data.repository

import com.top.compose.sample.bean.AppException
import com.top.compose.sample.bean.TResponse
import com.top.compose.sample.bean.User
import com.top.compose.sample.domain.WanAndroidClient
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor() : AccountRepository {

    override suspend fun login(username: String, password: String): TResponse<User> {

        val login = WanAndroidClient.getApiUrl.login(username, password)
        if (login.isSuccess()) {
            return login
        } else {
            throw AppException(login.errorCode, login.errorMsg)
        }
    }

}