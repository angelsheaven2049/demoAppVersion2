package com.angelsheaven.demo.data.storage

import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.angelsheaven.demo.data.storage.DatabaseContract.SELECT_ARTICLE_DETAIL
import com.angelsheaven.demo.testing.OpenForTesting
import com.angelsheaven.demo.utilities.MyLogger
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * StorageDataSource: handle request to manipulate data on
 * local storage
 */
@OpenForTesting
@Singleton
class StorageDataSource @Inject constructor(
    private val mDatabase: AppDatabase?
) : MyLogger {

    /**
     * Retrieve article detail from local data source
     */
    fun retrieveArticleDetail(mNewsId: Int): Flowable<Article>? {
        val queryNewsDetail = SELECT_ARTICLE_DETAIL.format(mNewsId)
        return mDatabase?.articleDao()
            ?.getNewsDetail(SimpleSQLiteQuery(queryNewsDetail))
    }

    /**
     * Delete all article in local storage
     */
    fun deleteAllArticles(): Int? {
        return mDatabase?.articleDao()?.deleteOldItems()
    }

    /**
     * Insert bulk of articles into local storage
     */
    fun insertBulkArticles(articles: List<Article>?): List<Long>? {
        return articles?.toTypedArray()?.run { mDatabase?.articleDao()?.insertArticles(*this) }
    }

    /**
     * Retrieve all articles in local storage
     */
    fun retrieveAllArticles(): DataSource.Factory<Int, Article>? {
        return mDatabase?.articleDao()?.getAllArticles()
    }

}
