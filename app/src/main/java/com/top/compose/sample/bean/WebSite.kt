package com.top.compose.sample.bean

//查用网站
data class WebSite(
    val category: String,
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)