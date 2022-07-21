package com.top.compose.sample.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//当前页面
@Entity(tableName = "cur_page")
data class CurPage(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "cur_page") val curPage: Int
)


