package com.top.compose.sample.business.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.top.compose.core.BaseViewModel
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.data.repository.WanAndroidRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


data class HomeUiState(
    val banners: List<Banner> = mutableListOf(),
    val articles: List<Article> = mutableListOf()
)

sealed class HomeUiAction {
    data class Praise(val id: Int) : HomeUiAction()
}

private const val VISIBLE_THRESHOLD = 5
private const val LAST_SEARCH_QUERY: String = "last_search_query"
private const val DEFAULT_QUERY = "Android"

//https://www.jianshu.com/p/ead76b6832a4
@HiltViewModel
class WanAndroidViewModel @Inject constructor(
    val wanAndroidRepository: WanAndroidRepositoryImpl,
    val savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {


    var userName: String?
        get() {
            return savedStateHandle.get<String>("Name")
        }
        set(value) {
            savedStateHandle.set("Name", value)
        }

    //val state: StateFlow<HomeUiState>

    //val accept: (HomeUiAction) -> Unit


    init {
        getBanner()
    }


    //方式1
    fun getArticleData(): Flow<PagingData<Article>> =
        wanAndroidRepository.article().cachedIn(viewModelScope)


    //方式 2
    val articles: Flow<PagingData<Article>> =
        wanAndroidRepository.articlePagingSource().cachedIn(viewModelScope)


    fun getBanner(): Flow<List<Banner>> {
        return wanAndroidRepository.banner()
    }


    override fun onCleared() {
        super.onCleared()
    }


}