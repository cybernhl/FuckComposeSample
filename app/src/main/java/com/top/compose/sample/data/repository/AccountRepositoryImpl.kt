package com.top.compose.sample.data.repository

import com.top.compose.sample.bean.User
import com.top.compose.sample.domain.AppException
import com.top.compose.sample.domain.TResponse
import com.top.compose.sample.domain.WanAndroidService
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor() : AccountRepository {

    @Inject
    lateinit var wanAndroidService: WanAndroidService

    override suspend fun login(username: String, password: String): TResponse<User> {

        val login = wanAndroidService.login(username, password)

        if (login.isSuccess()) {
            return login
        } else {
            //将请求错误归类为AppException
            throw AppException(login.errorCode, login.errorMsg)
        }
    }

    override suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): TResponse<User> {
        val register = wanAndroidService.register(username, password, repassword = repassword)

        if (register.isSuccess()) {
            return register
        } else {
            //将请求错误归类为AppException
            throw AppException(register.errorCode, register.errorMsg)
        }
    }

}