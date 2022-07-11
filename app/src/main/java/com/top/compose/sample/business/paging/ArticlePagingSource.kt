package com.top.compose.sample.business.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.top.compose.sample.bean.Article
import com.top.compose.sample.domain.WanAndroidService
import kotlinx.coroutines.delay
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(val wanAndroidService: WanAndroidService) :
    PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        //当 Paging 库因其后备 PagingSource 中的数据发生更改而需要重新加载界面项时，系统会调用该方法。
        return null
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return (article.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        try {
            val page = params.key ?: 0
            val pageSize = params.loadSize

            delay(2000)
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
        }catch (e:Exception){
            return LoadResult.Error(e)
        }

    }
}