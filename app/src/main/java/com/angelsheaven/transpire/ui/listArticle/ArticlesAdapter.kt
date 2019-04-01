/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.angelsheaven.transpire.ui.listArticle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.angelsheaven.transpire.R
import com.angelsheaven.transpire.data.storage.Article
import com.angelsheaven.transpire.utilities.MyLogger

/**
 * This class is used to hold and handle how to display
 * articles to user
 */
class ArticlesAdapter(
    val onUserClickOnItem: (Int) -> Unit
) : PagedListAdapter<Article, ArticlesViewHolder>(diffCallback), MyLogger {

    /**
     * View type to display top article and down articles
     * firtViewType: top article
     * downArticleView: down article
     */
    private val topArticleView = 1
    private val downArticleView = 2

    /**
     * Process which item is top article and otherwise
     * artile has attribute toparticle true is topArticleView
     * and otherwise
     */
    override fun getItemViewType(position: Int): Int {
        val article = getItem(position) as Article
        log("Top article ${article.topArticle} position $position")
        return if (article.topArticle) topArticleView else downArticleView
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {

        holder.bindTo(getItem(position))

        /**
         * Register listener to handle user click event on articles item
         */
        holder.itemView.setOnClickListener {
            if (position < itemCount) {
                this.getItem(position)?.run {
                    onUserClickOnItem(this.roomId)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        /**
         * If topArticleView set viewlayout is top_large_article_item_layout
         * otherwise article_item_layout
         */
        val layoutId = if (viewType == topArticleView) R.layout.top_large_article_item_layout else
            R.layout.article_item_layout

        val layoutView = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)

        return ArticlesViewHolder(layoutView)
    }


    /**
     * Handle should or shouldn't display article
     */
    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.roomId == newItem.roomId

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }

}
