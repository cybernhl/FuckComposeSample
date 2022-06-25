package com.top.compose.sample.domain

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WanAndroidClient {


    companion object {
        private lateinit var mWanAndroidService: WanAndroidService
        val getApiUrl: WanAndroidService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            WanAndroidClient().getRetrofitService()
        }
    }

    private fun getRetrofitService(): WanAndroidService {
        val restService = initRetrofit(initOkHttp()).create(WanAndroidService::class.java)
        return restService
    }


    private fun initRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BaseApi.BaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initOkHttp(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(LoggingInterceptor())
            .build()
    }
}