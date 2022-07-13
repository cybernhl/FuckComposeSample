package com.top.compose.sample.data.local

import androidx.room.Dao
import androidx.room.Query
import com.top.compose.sample.bean.Article

@Dao
interface ArticleDao {
//    @Insert
//    suspend fun insert(vararg articles: List<Article>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>
//    @Query("SELECT * FROM articles")
//    suspend fun getAllArticles(): Flow<List<Article>>

    //配合Paging;  返回 PagingSource
//    @Query("SELECT * FROM articles")
//    fun getArticles(): PagingSource<Int, Article>
}