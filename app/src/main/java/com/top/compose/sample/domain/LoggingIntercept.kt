package com.top.compose.sample.domain

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.lang.String
import java.util.*
import kotlin.Throws


class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        Log.e("LogInterceptor", "request:$request")
        Log.e("LogInterceptor", "request header:" + request.headers)
        val t1 = System.nanoTime()
        val response: Response = chain.proceed(chain.request())
        val t2 = System.nanoTime()
        Log.e(
            "LogInterceptor",
            String.format(
                Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request.url, (t2 - t1) / 1e6, response.headers
            )
        )
        val mediaType = response.body!!.contentType()
        val content = response.body!!.string()
        Log.e("LogInterceptor", "response body:$content")
        return response.newBuilder()
            .body(ResponseBody.create(mediaType, content))
            .build()
    }
}