package com.top.compose.sample

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.top.compose.sample.bean.Article
import com.top.compose.sample.data.local.ArticleDao
import com.top.compose.sample.data.local.WanAndroidDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DbReadWriteTest {

    private lateinit var articleDao: ArticleDao
    private lateinit var db: WanAndroidDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, WanAndroidDatabase::class.java
        ).build()
        articleDao = db.articleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val article = Article(
            0,
            "",
            0,
            "",
            true,
            0,
            "",
            true,
            0,
            "",
            "",
            "",
            false,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            1000,
            1000,
            0,
            1000,
            "",
            0,
            "",
            null,
            "",
            0,
            0,
            0,
            0,
            true
        )

        runBlocking {
            articleDao.insert(article)
        }
    }
}