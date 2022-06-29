package com.top.compose.sample.vm

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.top.compose.core.BaseViewModel
import com.top.compose.core.curApplication
import com.top.compose.sample.bean.User
import com.top.compose.sample.data.repository.AccountRepositoryImpl
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
                accountRepositoryImpl.login(account, password)
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