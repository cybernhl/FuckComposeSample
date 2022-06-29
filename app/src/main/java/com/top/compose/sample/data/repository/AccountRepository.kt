package com.top.compose.sample.data.repository

import com.top.compose.sample.bean.TResponse
import com.top.compose.sample.bean.User

interface AccountRepository {

    suspend fun login(username: String, password: String): TResponse<User>

    //suspend fun register(username: String, password: String): TResponse<User>

}