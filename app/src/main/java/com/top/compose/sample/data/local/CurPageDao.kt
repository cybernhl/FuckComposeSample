package com.top.compose.sample.data.local

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.top.compose.sample.bean.Article
import com.top.compose.sample.bean.CurPage

@Dao
interface CurPageDao {
    @Insert
    suspend fun setCurPage(curPage: CurPage)

    @Query("SELECT * FROM cur_page where id = 0")
    suspend fun getCurPage(): CurPage
}