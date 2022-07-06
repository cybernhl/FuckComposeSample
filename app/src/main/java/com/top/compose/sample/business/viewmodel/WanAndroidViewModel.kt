package com.top.compose.sample.business.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.top.compose.core.BaseViewModel
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.data.repository.WanAndroidRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


interface HomeUiStateee {
    val banner: List<Banner>?
    val article: PagingData<Article>?
}

sealed interface HomeUiState {
    val banner: List<Banner>?
    val article: Flow<PagingData<Article>>?
}

data class HasData(
    override val banner: List<Banner>?,
    override val article: Flow<PagingData<Article>>?
) : HomeUiState


data class HomeViewModelState(
    val banner: List<Banner>? = mutableListOf(),
    val article: Flow<PagingData<Article>>? = emptyFlow()
) {
    fun toUiState(): HomeUiState = HasData(banner = banner, article = article)
}


@HiltViewModel
class WanAndroidViewModel @Inject constructor() :
    BaseViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState())

    val uiStateee =  viewModelState.asStateFlow()

    val banner: List<Banner>? = null


    val uiState = viewModelState
        .map {
            it.toUiState()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    @Inject
    lateinit var wanAndroidRepository: WanAndroidRepositoryImpl


    fun getBannerr() {

        banner?.asFlow()

        viewModelScope.launch {
            val banner = wanAndroidRepository.bannerr()

            viewModelState.update {
                it.copy(banner = banner)
            }

        }
    }

    fun getA() {
        viewModelScope.launch {
            val banner = wanAndroidRepository.article()

            viewModelState.update {
                it.copy(banner = null)
            }

        }
    }

    fun getBanner(): Flow<List<Banner>> {
        return wanAndroidRepository.banner()
    }

    fun getArticleData(): Flow<PagingData<Article>> {
        return wanAndroidRepository.article().cachedIn(viewModelScope)
    }
}