package com.top.compose.sample.business.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.top.compose.sample.bean.Article

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator : RemoteMediator<Int, Article>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        when (loadType) {
            LoadType.REFRESH -> {

            }
            LoadType.PREPEND -> {

            }
            LoadType.APPEND -> {

            }
        }
    }
}