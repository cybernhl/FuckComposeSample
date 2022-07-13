package com.top.compose.sample.di

import android.app.Application
import androidx.room.Room
import com.top.compose.sample.data.local.ArticleDao
import com.top.compose.sample.data.local.ListTypeConverter
import com.top.compose.sample.data.local.WanAndroidDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {


    @Provides
    fun provideWanAndroidDatabase(@ApplicationContext context: Application): WanAndroidDatabase =
        Room.databaseBuilder(context.applicationContext, WanAndroidDatabase::class.java, "WanAndroid.db")
            .addTypeConverter(ListTypeConverter::class)
            .build()


    @Provides
    fun provideArticleDao(database: WanAndroidDatabase): ArticleDao {
        return database.articleDao()
    }
}
