package com.top.compose.sample.di



//@Module
//@InstallIn(SingletonComponent::class)
//class RoomModule {
//
//
////    @Singleton
////    @Provides
////    fun provideWanAndroidDatabase(@ApplicationContext context: Application): WanAndroidDatabase =
////        Room.databaseBuilder(context, WanAndroidDatabase::class.java, "WanAndroid.db")
////            .addTypeConverter(ListTypeConverter::class)
////            .build()
//
//
////    @Singleton
////    @Provides
////    fun provideArticleDao(database: WanAndroidDatabase): ArticleDao {
////        return database.articleDao()
////    }
//}