package com.top.compose.sample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.top.compose.sample.bean.Article
import kotlinx.coroutines.flow.Flow

////@Dao
//interface ArticleDao {
////    @Insert
////    suspend fun insert(vararg articles: List<Article>)
////
////    @Query("SELECT * FROM articles")
////    suspend fun getAllArticles(): Flow<List<Article>>
//}