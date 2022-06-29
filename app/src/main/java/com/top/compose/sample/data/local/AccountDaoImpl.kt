package com.top.compose.sample.data.local

import com.tencent.mmkv.MMKV
import com.top.compose.sample.bean.User
import javax.inject.Inject

class AccountDaoImpl : AccountDao {

    @Inject
    lateinit var defaultMMKV: MMKV


    override fun isLogin(): Boolean {
        return defaultMMKV.decodeBool("isLogin")
    }

    override fun setLogin(isLogin: Boolean) {

    }

    override fun getUser(): User? {
        val user = defaultMMKV.decodeParcelable("User", User::class.java)

        return user
    }

    override fun setUser(user: User) {

    }
}