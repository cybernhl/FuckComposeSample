package com.top.compose.sample.business.viewmodel

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.top.compose.core.BaseViewModel
import com.top.compose.core.curApplication
import com.top.compose.sample.bean.User
import com.top.compose.sample.data.repository.AccountRepositoryImpl
import com.top.compose.widget.showDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepositoryImpl: AccountRepositoryImpl,
) : BaseViewModel() {

    val user: MutableLiveData<User> = MutableLiveData<User>()
    val success: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    fun login(account: String, password: String) {
        viewModelScope.launch {
            runCatching {
                loading.showLoading.postValue("uuuuuuuuuuuu")
                accountRepositoryImpl.login(account, password)
            }.onSuccess {
                success.postValue(it.isSuccess())
                user.postValue(it.data)
            }.onFailure {
                success.postValue(false)
                Toast.makeText(curApplication(), it.message, Toast.LENGTH_SHORT).show()
            }
            loading.dismissLoading.postValue(false)
        }
    }


    fun register(account: String, password: String, repassword: String) {
        viewModelScope.launch {
            runCatching {
                accountRepositoryImpl.register(account, password, repassword)
            }.onSuccess {
                success.postValue(it.isSuccess())
                user.postValue(it.data)
            }.onFailure {
                success.postValue(false)
                Toast.makeText(curApplication(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}