package com.top.compose.sample.data.local

import androidx.room.Database
import androidx.room.ProvidedAutoMigrationSpec
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.top.compose.sample.bean.Article
@ProvidedAutoMigrationSpec
@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(ListTypeConverter::class)
abstract class WanAndroidDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao


}