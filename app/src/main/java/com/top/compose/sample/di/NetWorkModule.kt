package com.top.compose.sample.di

import com.top.compose.sample.domain.BaseApi
import com.top.compose.sample.domain.LoggingInterceptor
import com.top.compose.sample.domain.WanAndroidService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @LoggingInterceptorOkHttpClient
    @Provides
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(LoggingInterceptor())
        .build()

    @Provides
    fun provideWanAndroidService(@LoggingInterceptorOkHttpClient okHttpClient: OkHttpClient): WanAndroidService =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BaseApi.BaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WanAndroidService::class.java)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptorOkHttpClient