package com.top.compose.sample.data.local

import com.tencent.mmkv.MMKV
import com.top.compose.sample.bean.User
import com.top.compose.sample.di.DefaultDispatcher
import com.top.compose.sample.di.DefaultMMKV
import kotlinx.coroutines.CoroutineDispatcher

class AccountDaoImpl : AccountDao {

    @DefaultMMKV
    private lateinit var defaultMMKV: MMKV


    override fun isLogin(): Boolean {
        return defaultMMKV.decodeBool("isLogin")
    }

    override fun getUser(): User? {
        val user = defaultMMKV.decodeParcelable("User", User::class.java)

        return user
    }
}