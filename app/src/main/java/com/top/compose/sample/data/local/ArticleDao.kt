package com.top.compose.sample.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.top.compose.sample.bean.Article

@Dao
interface ArticleDao {
    @Insert
    suspend fun insert(articles: List<Article>)

    @Delete
    suspend fun delete(article: Article)

    @Query("DELETE FROM articles")
    suspend fun clear()

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>


//    @Query("SELECT * FROM articles")
//    suspend fun getAllArticles(): Flow<List<Article>>

   // 配合Paging;  返回 PagingSource
    @Query("SELECT * FROM articles")
    fun getArticles(): PagingSource<Int, Article>

//    @Query("SELECT * FROM articles")
//    suspend fun lastUpdated():Long
}