package com.top.compose.sample.data.local

import com.tencent.mmkv.MMKV
import com.top.compose.sample.bean.User
import javax.inject.Inject

class AccountDaoImpl @Inject constructor() : AccountDao {

    @Inject
    lateinit var defaultMMKV: MMKV


    override fun isLogin(): Boolean {
        return defaultMMKV.decodeBool("isLogin")
    }

    override fun setLogin(isLogin: Boolean) {

    }

    override fun getUser(): User? {

        return defaultMMKV.decodeParcelable("User", User::class.java)
    }

    override fun setUser(user: User) {
        defaultMMKV.encode("User",user)
    }
}