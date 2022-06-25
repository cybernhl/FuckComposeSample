package com.top.compose.sample.bean

import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val admin: Boolean,
    val chapterTops: List<String>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)