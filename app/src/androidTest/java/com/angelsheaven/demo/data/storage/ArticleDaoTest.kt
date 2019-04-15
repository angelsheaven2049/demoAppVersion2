package com.angelsheaven.demo.data.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.test.runner.AndroidJUnit4
import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.data.ViewModelContract
import com.angelsheaven.demo.util.TestUtil
import com.angelsheaven.demo.utilities.MyLogger
import io.reactivex.internal.util.NotificationLite.getValue
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest : DbTest(), MyLogger {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {
        val article = TestUtil.createArticle()
        db.articleDao().insertArticles(article)

        val loadedArticle = getValue<DataSource.Factory<Int, Article>>(
            db.articleDao()
                .getAllArticles()
        )

        assertNotNull(loadedArticle)

        var formatPagingData: LiveData<PagedList<Article>>? = null

        loadedArticle?.run {
            formatPagingData = LivePagedListBuilder(this, ViewModelContract.configLoadData)
                .setInitialLoadKey(null)
                .build()

            assertNotNull(formatPagingData)

            formatPagingData?.run {
                assertNotNull(this.value)
            }
        }


    }

}