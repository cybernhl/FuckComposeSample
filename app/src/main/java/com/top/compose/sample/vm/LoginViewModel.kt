package com.top.compose.sample.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.top.compose.sample.bean.User
import com.top.compose.sample.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    val user: MutableLiveData<User> = MutableLiveData<User>()

    val isLogin: MutableState<Boolean> by lazy {
        mutableStateOf(false)
    }

    fun login() {
        GlobalScope.launch {
            val login = userRepository.login("leo94666", "13524653020yANg")
            login.let {
                user.postValue(it)
            }
        }
    }
}