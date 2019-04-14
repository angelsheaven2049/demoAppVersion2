package com.angelsheaven.demo.data.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.test.runner.AndroidJUnit4
import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.util.TestUtil
import io.reactivex.Flowable
import io.reactivex.internal.util.NotificationLite.getValue
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {
        val article = TestUtil.createArticle()
        db.articleDao().insertArticles(article)

        val articleDetailQuery = DatabaseContract.SELECT_ARTICLE_DETAIL.format(0)
        val loadedArticle = getValue<Flowable<Article>>(
            db.articleDao()
                .getNewsDetail(SimpleSQLiteQuery(articleDetailQuery))
        )

        assertNotNull(loadedArticle)

    }

}