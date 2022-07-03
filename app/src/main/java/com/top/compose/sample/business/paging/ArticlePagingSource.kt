package com.top.compose.sample.business.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.top.compose.sample.bean.Article
import com.top.compose.sample.domain.WanAndroidService
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(val wanAndroidService: WanAndroidService) :
    PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val page = params.key ?: 0
        val pageSize = params.loadSize
        val articleResponse = wanAndroidService.article(page)

        val article = articleResponse.data.datas

        var topArticle: List<Article> = listOf()

        if (page == 0) {
            topArticle = wanAndroidService.topArticle().data

            topArticle.map {
                it.top = true
            }
        }
        val prevKey = if (page > 1) page - 1 else null
        val nextKey = if (article.isNotEmpty()) page + 1 else null
        return LoadResult.Page(topArticle + article, prevKey = prevKey, nextKey = nextKey)
    }
}