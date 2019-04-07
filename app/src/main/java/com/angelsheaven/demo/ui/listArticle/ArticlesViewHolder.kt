package com.angelsheaven.demo.ui.listArticle

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.utilities.ImageRequester
import com.angelsheaven.demo.utilities.MyLogger
import kotlinx.android.synthetic.main.article_item_layout.view.*

/**
 * This class is used to handle how to display
 * data on each row of article list
 * @see ArticlesViewHolder
 */
class ArticlesViewHolder(holderView: View) :
    RecyclerView.ViewHolder(holderView)
    , MyLogger, View.OnClickListener {

    init {
        holderView.setOnClickListener(this)
    }

    /**
     * Bind data to Wigetview on article item layout
     */
    private lateinit var mNews: Article
    private lateinit var mOnUserClickOnItem: (Int) -> Unit

    fun bindTo(news: Article, onUserClickOnItem: (Int) -> Unit) {

        mNews = news
        mOnUserClickOnItem = onUserClickOnItem

        with(mNews) {
            itemView.tv_article_title.text = title
            itemView.tv_article_publish_date.text = getFormattedPublishTime()
            getThumbnailUrl()?.run { ImageRequester.setImageFromUrl(itemView.image_article, this) }
        }

    }

    override fun onClick(view: View?) {
        /**
         * Register listener to handle user click event on articles item
         */
        mOnUserClickOnItem(mNews.roomId)
    }

}