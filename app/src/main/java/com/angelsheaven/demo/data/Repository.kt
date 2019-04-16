package com.angelsheaven.demo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.angelsheaven.demo.data.ViewModelContract.configLoadData
import com.angelsheaven.demo.data.network.NetworkContract
import com.angelsheaven.demo.data.network.NetworkContract.parseArticles
import com.angelsheaven.demo.data.network.NetworkDataSource
import com.angelsheaven.demo.data.network.ServerResponse
import com.angelsheaven.demo.data.network.volley.RequestResult
import com.angelsheaven.demo.data.storage.StorageDataSource
import com.angelsheaven.demo.testing.OpenForTesting
import com.angelsheaven.demo.utilities.MyLogger
import io.reactivex.Flowable
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository: this class is used to handle data request
 * it decides which sources it should get (network or local storage)
 */
@OpenForTesting
@Singleton
class Repository @Inject constructor(
    private val networkDataSource: NetworkDataSource?,
    private val storageDataSource: StorageDataSource?
) : MyLogger {

    /**
     * Observer for network error
     */
    private val networkErrors by lazy { MutableLiveData<Int>() }
    val isCompleteReloadingData by lazy { MutableLiveData<Boolean>() }

    /**
     * Handle request load data from user
     * @return a result of loading article
     */
    fun loadArticles(): ArticleLoadResult? {

        /**
         * Download and save data
         */
        downloadAndSaveData()

        /**
         * Make a data query and return observer for data change
         */
        val queryData =
            storageDataSource?.retrieveAllArticles()

        var formatPagingData: LiveData<PagedList<Article>>? = null

        queryData?.run {
            formatPagingData = LivePagedListBuilder(this, configLoadData)
                .setInitialLoadKey(null)
                .build()
        }

        return ArticleLoadResult(formatPagingData, networkErrors)
    }

    /**
     * Download articles from server
     * and save it into local storage
     */
    @Synchronized
    private fun downloadAndSaveData() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                /**
                 * Request network server return just in articles
                 */
                val getDataTask = async { networkDataSource?.getJustInNewsAsyncRetrofit() }

                val getReturnedData = getDataTask.await()

                getReturnedData?.let { result ->
                    when (result) {
                        is RequestResult.ReturnedData<*> -> {

                            val serverResponse = result.data as ServerResponse

                            try {

                                val downloadedArticleList = parseArticles(serverResponse)

                                /**
                                 * if downloaded article list is not null or empty then save it into local database
                                 */
                                if (!downloadedArticleList.isNullOrEmpty()) {
                                    /**
                                     * Set true to the attribute top article
                                     * of first article in the downloaded list
                                     */
                                    downloadedArticleList.first().topArticle = true

                                    /**
                                     * Clear all old articles to make room for new articles
                                     */
                                    val taskDeleteAllArticle = async { storageDataSource?.deleteAllArticles() }

                                    val resultDeleteAllArticleTask = taskDeleteAllArticle.await()

                                    if (resultDeleteAllArticleTask != null) {
                                        /**
                                         * Insert all downloaded articles to local storage
                                         */
                                        val taskInsertBurkArticle = async {
                                            storageDataSource
                                                ?.insertBulkArticles(downloadedArticleList)
                                        }

                                        val resultInsertBulkArticle = taskInsertBurkArticle.await()

                                        if (resultInsertBulkArticle != null
                                            && resultInsertBulkArticle.isNotEmpty()
                                        ) {
                                            log("Insert ${resultInsertBulkArticle.size} articles success")
                                            isCompleteReloadingData.postValue(true)
                                        }
                                    }
                                }

                            } catch (serverException: NetworkContract.ServerException) {
                                networkErrors.postValue(NetworkContract.SERVER_ERROR)
                            } catch (ex: Exception) {
                                networkErrors.postValue(NetworkContract.UNKNOWN_ERROR)
                            }

                        }

                        is RequestResult.ReturnedError -> {
                            log("Error ${result.error}")
                            networkErrors.postValue(result.error)
                        }
                    }

                }
            }
        }
    }

    /**
     * Handle request retrieving article detail
     */
    fun retrieveArticleDetail(articleId: Int): Flowable<Article>? {
        return storageDataSource?.retrieveArticleDetail(articleId)
    }

    /**
     * Handle request reset network error message
     */
    fun resetNetWorkErrorValue() {
        networkErrors.postValue(null)
    }


}
