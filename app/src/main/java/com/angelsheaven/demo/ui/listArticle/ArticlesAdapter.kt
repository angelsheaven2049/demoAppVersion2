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

package com.angelsheaven.demo.ui.listArticle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.angelsheaven.demo.DemoApplication
import com.angelsheaven.demo.R
import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.databinding.ArticleItemLayoutBinding
import com.angelsheaven.demo.databinding.TopLargeArticleItemLayoutBinding
import com.angelsheaven.demo.utilities.MyLogger
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * This class is used to hold and handle how to display
 * articles to user
 */
class ArticlesAdapter(
    private val onUserClickOnItem: (Int) -> Unit
) : PagedListAdapter<Article, ArticlesAdapter.ArticlesViewHolder>(diffCallback)
    , ArticleClickListener, MyLogger {

    /**
     * View type to display top article and down articles
     * 1: top article type
     * 2: below article type
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
        return if (article.topArticle) topArticleView else downArticleView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        /**
         * If topArticleView set viewlayout is top_large_article_item_layout
         * otherwise article_item_layout
         */

        val layoutId = if (viewType == topArticleView) R.layout.top_large_article_item_layout else
            R.layout.article_item_layout

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId, parent, false
        )

        return ArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {

        val article = getItem(position)

        article?.run {
            holder.bindTo(this, this@ArticlesAdapter)
        }

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

    override fun onArticleClicked(articleId: Int) {
        log("user click $articleId")
        onUserClickOnItem(articleId)
    }

    /**
     * This class is used to handle how to display
     * data on each row of article list
     * @see ArticlesViewHolder
     */
    inner class ArticlesViewHolder(private val mBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(mBinding.root)
        , MyLogger {

        /**
         * Bind data to Wigetview on article item layout
         */
        private lateinit var mArticle: Article

        fun bindTo(news: Article, listener: ArticleClickListener) {

            mArticle = news

            with(mArticle) {

                if (mBinding is ArticleItemLayoutBinding) {
                    mBinding.article = mArticle
                    mBinding.articleClickListener = listener
                } else {
                    (mBinding as TopLargeArticleItemLayoutBinding).article = mArticle
                    mBinding.articleClickListener = listener
                }

                mBinding.executePendingBindings()

                getThumbnailUrl()?.run {
                    Glide.with(DemoApplication.instance)
                        .load(this)
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                        .apply(RequestOptions.fitCenterTransform())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(itemView.findViewById(R.id.image_article))
                }

            }

        }


    }

}

interface ArticleClickListener {
    fun onArticleClicked(articleId: Int)
}


