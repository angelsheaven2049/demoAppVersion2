package com.angelsheaven.demo.util

import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.data.Enclosure

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
        enclosure = createEnclosure(),
        categories = createCategories(),
        topArticle = false
    )

    private fun createEnclosure() = Enclosure(
        link = "",
        type = "",
        thumbnail = ""
    )

    private fun createCategories() = listOf("")

}