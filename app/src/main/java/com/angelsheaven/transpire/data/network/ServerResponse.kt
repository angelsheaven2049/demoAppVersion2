package com.angelsheaven.transpire.data.network

import com.angelsheaven.transpire.data.storage.Article
import com.angelsheaven.transpire.data.storage.Feed

/**
 * This class defines response object
 * from server
 */
data class ServerResponse(
    val status: String? = null,
    val feed: Feed? = null,
    val items: List<Article>? = null
)