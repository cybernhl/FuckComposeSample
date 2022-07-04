package com.top.compose.sample.business.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.top.compose.core.BaseViewModel
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.data.repository.WanAndroidRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class WanAndroidViewModel @Inject constructor(
    private val wanAndroidRepository: WanAndroidRepositoryImpl
) :
    BaseViewModel() {


    var banner = MutableLiveData<Array<Banner>>()

    fun getBanner(): Flow<List<Banner>> {
        return wanAndroidRepository.banner()
    }

    fun getArticleData(): Flow<PagingData<Article>> {
        return wanAndroidRepository.article().cachedIn(viewModelScope)
    }
}