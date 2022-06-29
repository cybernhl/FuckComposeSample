package com.top.compose.sample.data.local

import com.top.compose.sample.bean.User

interface AccountDao {

    fun isLogin(): Boolean
    fun getUser(): User?
}