package com.top.compose.sample.domain

import com.top.compose.sample.bean.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//https://www.wanandroid.com/blog/show/2
//
interface WanAndroidService {


    @POST("user/loginj")
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
    ): Call<String>
}