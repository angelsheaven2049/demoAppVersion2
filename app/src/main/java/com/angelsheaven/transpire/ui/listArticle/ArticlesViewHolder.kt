package com.angelsheaven.transpire.ui.listArticle

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.angelsheaven.transpire.data.storage.Article
import com.angelsheaven.transpire.utilities.ImageRequester
import com.angelsheaven.transpire.utilities.MyLogger
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
    fun bindTo(news: Article?) {
        news?.run {
            itemView.tv_article_title.text = this.title
            itemView.tv_article_publish_date.text = this.getFormattedPublishDate()
            itemView.tv_article_publish_time.text = this.getFormattedPublishTime()
            this.getThumbnailUrl()?.run { ImageRequester.setImageFromUrl(itemView.image_article, this) }
        }
    }
}