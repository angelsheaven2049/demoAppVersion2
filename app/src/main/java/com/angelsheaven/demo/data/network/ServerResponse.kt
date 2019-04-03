package com.angelsheaven.demo.data.network

import com.angelsheaven.demo.data.storage.Article
import com.angelsheaven.demo.data.storage.Feed

/**
 * This class defines response object
 * from server
 */
data class ServerResponse(
    val status: String,
    val feed: Feed? = null,
    val items: List<Article>? = null
)