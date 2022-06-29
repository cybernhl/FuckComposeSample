package com.top.compose.sample.data.local

import com.top.compose.sample.bean.User

interface AccountDao {

    fun isLogin(): Boolean

    fun setLogin(isLogin: Boolean)

    fun getUser(): User?

    fun setUser(user: User)
}