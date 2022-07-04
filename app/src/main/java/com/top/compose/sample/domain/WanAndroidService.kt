package com.top.compose.sample.domain

import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.Banner
import com.top.compose.sample.bean.HomeArticle
import com.top.compose.sample.bean.User
import retrofit2.http.*

//https://www.wanandroid.com/blog/show/2
//
interface WanAndroidService {


    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): TResponse<User>

    @POST("user/register")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): TResponse<User>

    @GET("user/logout/json")
    @FormUrlEncoded
    fun logout(
        @Field("username") username: String,
        @Field("password") password: String
    ): TResponse<User>


    @GET("article/list/{id}/json")
    suspend fun article(
        @Path("id") id: Int
    ): TResponse<HomeArticle>


    @GET("article/top/json")
    suspend fun topArticle(): TResponse<List<Article>>


    @GET("banner/json")
    suspend fun banner(): TResponse<List<Banner>>
}