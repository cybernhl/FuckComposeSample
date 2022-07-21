package com.top.compose.sample.data.local

import androidx.room.Database
import androidx.room.ProvidedAutoMigrationSpec
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.CurPage

@Database(
    entities = [Article::class,
        CurPage::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(ListTypeConverter::class)
abstract class WanAndroidDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    abstract fun curPageDao(): CurPageDao
}