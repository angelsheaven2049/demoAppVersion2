package com.angelsheaven.demo.data.storage

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.data.storage.DatabaseContract.DELETE_ALL_ARTICLES
import com.angelsheaven.demo.data.storage.DatabaseContract.GET_ARTICLES
import io.reactivex.Flowable

/**
 * An interface to indicate hold to component
 * manipulate on local storage
 */
@Dao
interface ArticleDao{

    @Query(DELETE_ALL_ARTICLES)
    fun deleteOldItems(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(vararg articles: Article):List<Long>?

    @Query(GET_ARTICLES)
    fun getAllArticles(): DataSource.Factory<Int, Article>

    @RawQuery(observedEntities = [Article::class])
    fun getNewsDetail(rawQuery: SupportSQLiteQuery): Flowable<Article>

}
