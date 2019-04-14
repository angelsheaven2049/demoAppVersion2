package com.angelsheaven.demo.util

import com.angelsheaven.demo.data.Article

object TestUtil {

    fun createArticle() = Article(
        title = "",
        pubDate = "",
        link = "",
        guid = "",
        author = "",
        thumbnail = "",
        description = "",
        content = "",
        enclosure = null,
        categories = null,
        topArticle = false
    )

}