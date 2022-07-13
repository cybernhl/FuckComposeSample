package com.top.compose.sample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.top.compose.sample.bean.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListTypeConverter::class)
abstract class WanAndroidDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao


}