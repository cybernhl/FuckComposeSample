package com.top.compose.sample.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.top.compose.sample.bean.Article
import com.top.compose.sample.data.local.WanAndroidDatabase
import com.top.compose.sample.domain.WanAndroidService

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator(
    val wanAndroidService: WanAndroidService,
    val wanAndroidDatabase: WanAndroidDatabase
) :
    RemoteMediator<Int, Article>() {

//    override suspend fun initialize(): InitializeAction {
//        //InitializeAction.LAUNCH_INITIAL_REFRESH 刷新本地数据
//        //InitializeAction.SKIP_INITIAL_REFRESH 本地数据不需要刷新,跳过远程刷新并加载缓存的数据
//
//
//        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
//
//        val lastUpdated = wanAndroidDatabase.articleDao().lastUpdated()
//
//        if (System.currentTimeMillis() - lastUpdated >= cacheTimeout) {
//            return InitializeAction.LAUNCH_INITIAL_REFRESH
//        } else {
//            return InitializeAction.SKIP_INITIAL_REFRESH
//        }
//    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        //1.根据加载类型和到目前为止已加载的数据，确定要从网络中加载哪个页面
        val loadKey = when (loadType) {
            LoadType.REFRESH -> {
                //首次访问
                // 调用 PagingDataAdapter.refresh()
                0
            }
            LoadType.PREPEND -> {
                //在当前列表头部添加数据的时候使用

                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                //在下拉加载更多数据的时候使用
                val last = state.lastItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                last.id
            }
        }


        try {
            //2. 请求网络
            val article = wanAndroidService.article(loadKey).data.datas
            val endOfPaginationReached = article.size < state.config.pageSize

            //3.将网络数据插入到本地数据
            wanAndroidDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    wanAndroidDatabase.articleDao().clear()
                }
                //wanAndroidDatabase.articleDao().insertt(article[0])
                wanAndroidDatabase.articleDao().insert(article)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }


    }
}