package com.top.compose.sample.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.top.compose.sample.bean.AppException
import com.top.compose.sample.bean.BaseResponse
import com.top.compose.sample.bean.User
import com.top.compose.sample.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    val user: MutableLiveData<User> = MutableLiveData<User>()
    val success: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    /**
     * 过滤服务器结果，失败抛异常
     * @param block 请求体方法，必须要用suspend关键字修饰
     * @param success 成功回调
     * @param error 失败回调 可不传
     * @param isShowDialog 是否显示加载框
     * @param loadingMessage 加载框提示内容
     */
    fun <T> request(
        block: suspend () -> BaseResponse<T>,
        success: (T) -> Unit,
        error: (AppException) -> Unit = {},
        isShowDialog: Boolean = false,
        loadingMessage: String = "请求网络中..."
    ): Job {
        //如果需要弹窗 通知Activity/fragment弹窗
        return viewModelScope.launch {
            runCatching {
                if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
                //请求体
                block()
            }.onSuccess {
                //网络请求成功 关闭弹窗
                //loadingChange.dismissDialog.postValue(false)
                runCatching {
                    //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                    executeResponse(it) { t ->
                        success(t)
                    }
                }.onFailure { e ->
                    //打印错误消息
                    e.message?.loge()
                    //打印错误栈信息
                    e.printStackTrace()
                    //失败回调
                    error(ExceptionHandle.handleException(e))
                }
            }.onFailure {
                //网络请求异常 关闭弹窗
                loadingChange.dismissDialog.postValue(false)
                //打印错误消息
                it.message?.loge()
                //打印错误栈信息
                it.printStackTrace()
                //失败回调
                error(ExceptionHandle.handleException(it))
            }
        }
    }

    fun login(account: String, password: String) {

        request({userRepository.login(account,password)},{})
        GlobalScope.launch {
            //val login = userRepository.login("leo94666", "13524653020yANg")
            val login = userRepository.login(account, password)

            if (login.isSuccess()) {
                success.postValue(login.isSuccess())
            }

            login.let {
                user.postValue(it.data)
            }
        }
    }
}