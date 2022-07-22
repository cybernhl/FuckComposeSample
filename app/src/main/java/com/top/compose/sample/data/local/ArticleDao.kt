package com.top.compose.sample.data.local

import androidx.annotation.WorkerThread
import androidx.paging.PagingSource
import androidx.room.*
import com.top.compose.sample.bean.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    suspend fun insert(articles: List<Article>)

    @Insert
    suspend fun insert(article: Article)

    @Transaction
    suspend fun insertAll(objects: List<Article>) = objects.forEach {
        insert(it)
    }

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
    fun getArticlesPagingSource(): PagingSource<Int, Article>

//    @Query("SELECT * FROM articles")
//    suspend fun lastUpdated():Long
}