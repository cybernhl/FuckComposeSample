package com.top.compose.sample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.business.paging.ArticlePagingSource
import com.top.compose.sample.domain.AppException
import com.top.compose.sample.domain.WanAndroidService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WanAndroidRepositoryImpl @Inject constructor() : WanAndroidRepository {

    @Inject
    lateinit var wanAndroidService: WanAndroidService



    fun articlePagingSource() = ArticlePagingSource(wanAndroidService)



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