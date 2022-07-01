package com.top.compose.sample.business.viewmodel

import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.top.compose.core.BaseViewModel
import com.top.compose.core.curApplication
import com.top.compose.sample.bean.User
import com.top.compose.sample.data.local.AccountDaoImpl
import com.top.compose.sample.data.repository.AccountRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepositoryImpl: AccountRepositoryImpl,
    private val accountDaoImpl: AccountDaoImpl
) : BaseViewModel() {

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            it.postValue(accountDaoImpl.getUser())
        }
    }

    val isLogin: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            it.postValue(accountDaoImpl.isLogin())
        }
    }


    fun getUser() = user.postValue(accountDaoImpl.getUser())


    fun login(account: String, password: String) {
        viewModelScope.launch {
            runCatching {
                loading.postValue(true)
                accountRepositoryImpl.login(account, password)
            }.onSuccess {
                user.postValue(it.data)
                isLogin.postValue(true)
                accountDaoImpl.setUser(it.data)
                accountDaoImpl.setLogin(true)
            }.onFailure {
                Toast.makeText(curApplication(), it.message, Toast.LENGTH_SHORT).show()
            }
            loading.postValue(false)
            //isLogin.postValue(false)
        }
    }


    fun register(account: String, password: String, repassword: String) {
        viewModelScope.launch {
            runCatching {
                accountRepositoryImpl.register(account, password, repassword)
            }.onSuccess {
                user.postValue(it.data)
            }.onFailure {
                Toast.makeText(curApplication(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        getUser()
    }
}