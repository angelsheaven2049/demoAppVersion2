package com.angelsheaven.transpire.ui.listArticle

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.angelsheaven.transpire.R
import com.angelsheaven.transpire.data.ArticleLoadResult
import com.angelsheaven.transpire.data.ArticleRepository
import com.angelsheaven.transpire.data.network.NetworkContract
import com.angelsheaven.transpire.data.storage.Article
import com.angelsheaven.transpire.utilities.MyLogger
import javax.inject.Inject

/**
 * A viewmodel class for ListNewsFragment:
 * used to hold data and conduct query get data
 * to repository
 * @author Quan Nguyen
 * @see ArticleRepository
 * @see DisplayListArticlesFragmentViewModel
 */
class DisplayListArticlesFragmentViewModel @Inject constructor(private val repository:ArticleRepository,
                                                               private val app: Application): ViewModel(), MyLogger {

    private val queryLiveData = MutableLiveData<Bundle>()

    /**
     * an observer for complete data retrieving
     */
    val isCompleteReloadingData by lazy { repository.isCompleteReloadingData }

    private val articlesResult: LiveData<ArticleLoadResult> = Transformations.map(queryLiveData) {
        repository.loadArticles()
    }

    /**
     * an observer for just in articles
     */
    internal val articles: LiveData<PagedList<Article>> = Transformations.switchMap(articlesResult) {
        it.data
    }

    /**
     * observer for network errors
     */
    internal val networkErrors: LiveData<Int> = Transformations.switchMap(articlesResult) {
        it.networkErrors
    }

    /**
     * handle request load articles from user
     */
    @Synchronized
    fun loadArticles() {
        queryLiveData.postValue(null)
    }

    /**
     * Handle request send notification
     * displayed network error message
     * to user
     */
    fun notifyNetworkErrorDisplayed() {
        repository.resetNetWorkErrorValue()
    }

    fun getErrorMessage(errorCode: Int?): String {
        return when (errorCode) {
            NetworkContract.SERVER_ERROR -> app.getString(R.string.serverResponseErrorNotification)

            NetworkContract.NO_INTERNET_ACCESS -> app.getString(R.string.unableToAccessServerNotification)

            else -> app.getString(R.string.unknownErrorNotification)
        }

    }

}