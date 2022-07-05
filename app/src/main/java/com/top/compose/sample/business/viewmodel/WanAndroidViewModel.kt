package com.top.compose.sample.business.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.top.compose.core.BaseViewModel
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.data.repository.WanAndroidRepository
import com.top.compose.sample.data.repository.WanAndroidRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.cache
import javax.inject.Inject


sealed interface HomeUiState {

    val banner: Array<Banner>
    val article: Flow<PagingData<Article>>
}

data class NoData(
    override val banner: Array<Banner>,
    override val article: Flow<PagingData<Article>>
) : HomeUiState


data class HasData(
    override val banner: Array<Banner>,
    override val article: Flow<PagingData<Article>>
) : HomeUiState


private data class HomeViewModelState(
    val banner: Array<Banner>,
    val article: Flow<PagingData<Article>>
) {

    fun toUiState(): HomeUiState = HasData(banner = banner, article = article)
}


@HiltViewModel
class WanAndroidViewModel @Inject constructor() :
    BaseViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(null,null))




    @Inject
    lateinit var wanAndroidRepository: WanAndroidRepositoryImpl

    var banner = MutableLiveData<Array<Banner>>()

    fun getBanner(): Flow<List<Banner>> {
        return wanAndroidRepository.banner()
    }

    fun getArticleData(): Flow<PagingData<Article>> {
        return wanAndroidRepository.article().cachedIn(viewModelScope)
    }
}