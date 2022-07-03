package com.top.compose.sample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.business.paging.ArticlePagingSource
import com.top.compose.sample.domain.WanAndroidService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WanAndroidRepositoryImpl @Inject constructor() : WanAndroidRepository {

    @Inject
    lateinit var wanAndroidService: WanAndroidService


    override fun article(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { ArticlePagingSource(wanAndroidService) }).flow
    }

    override fun banner(): Flow<List<Banner>> {
        return wanAndroidService.banner().map {
            it.data
        }
    }


}