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
    , MyLogger {

    /**
     * Bind data to Wigetview on article item layout
     */
    fun bindTo(news: Article) {
        itemView.tv_article_title.text = news.title
        itemView.tv_article_publish_date.text = news.getFormattedPublishDate()
        itemView.tv_article_publish_time.text = news.getFormattedPublishTime()
        news.getThumbnailUrl()?.run { ImageRequester.setImageFromUrl(itemView.image_article, this) }
    }
}