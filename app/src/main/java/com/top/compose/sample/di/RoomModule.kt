package com.top.compose.sample.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.top.compose.sample.data.local.ArticleDao
import com.top.compose.sample.data.local.ListTypeConverter
import com.top.compose.sample.data.local.WanAndroidDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {


    @Provides
    fun provideWanAndroidDatabase(@ApplicationContext context: Context): WanAndroidDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            WanAndroidDatabase::class.java,
            "WanAndroid.db"
        ).addCallback(object :RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

            }
        })

            .build()


    @Provides
    fun provideArticleDao(database: WanAndroidDatabase): ArticleDao {
        return database.articleDao()
    }
}
