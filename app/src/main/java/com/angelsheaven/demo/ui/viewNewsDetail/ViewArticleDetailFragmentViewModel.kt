package com.angelsheaven.demo.ui.viewNewsDetail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.angelsheaven.demo.data.Repository
import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.utilities.MyLogger
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * ViewNewsDetailFragmentViewModel: view model class for
 * ViewDetailFragment used to query article detail from database
 * and reflect data change on xml layout
 * @author Quan Nguyen
 * @see fragment_view_article_detail.xml
 */
class ViewNewsDetailFragmentViewModel @Inject constructor(private val repository:Repository) : ViewModel(), MyLogger{

    /**
     * Used to reflect data change on xml layout
     * @see fragment_view_article_detail.xml
     */
    val articleDetail = ObservableField<Article>(Article())

    /**
     * Query article detail from local database
     */
    fun getArticleDetailObservable(newsId: Int): Flowable<Article>? {
        return repository.retrieveArticleDetail(newsId)
    }

}
