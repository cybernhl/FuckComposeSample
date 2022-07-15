package com.top.compose.sample.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.data.paging.ArticlePagingSource
import com.top.compose.sample.data.paging.ArticleRemoteMediator
import com.top.compose.sample.data.local.WanAndroidDatabase
import com.top.compose.sample.domain.AppException
import com.top.compose.sample.domain.WanAndroidService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WanAndroidRepositoryImpl @Inject constructor() : WanAndroidRepository {

    @Inject
    lateinit var wanAndroidService: WanAndroidService

    @Inject
    lateinit var wanAndroidDatabase: WanAndroidDatabase


    //fun articlePagingSource() = ArticleRemoteMediator(wanAndroidService, wanAndroidDatabase)


//    @OptIn(ExperimentalPagingApi::class)
//    fun articlePagingSource(): Flow<PagingData<Article>> = Pager(
//        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
//        remoteMediator = ArticleRemoteMediator(wanAndroidService, wanAndroidDatabase)
//    ) {
//        wanAndroidDatabase.articleDao().getArticles()
//    }.run {
//        this.flow
//    }


    @OptIn(ExperimentalPagingApi::class)
    fun articlePagingSource(): Flow<PagingData<Article>> {
        val articles = wanAndroidDatabase.articleDao().getArticles()
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = ArticleRemoteMediator(wanAndroidService, wanAndroidDatabase)
        ) {
            articles
        }.flow
    }



    override fun article(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { ArticlePagingSource(wanAndroidService) }).flow.catch {
            throw AppException(1003, it.message)
        }
    }


    override fun banner(): Flow<List<Banner>> {

        return flow {
            val data = wanAndroidService.banner().data
            emit(data)
        }.catch {
            throw AppException(1002, it.message)
        }
    }

    override suspend fun bannerr(): List<Banner> {
        return wanAndroidService.banner().data
    }


}