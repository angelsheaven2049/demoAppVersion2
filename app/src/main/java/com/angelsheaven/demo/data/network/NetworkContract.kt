package com.angelsheaven.demo.data.network

import com.angelsheaven.demo.data.storage.Article

/**
 * a contract defines parameters for
 * manipulation between
 */
object NetworkContract {

    const val BASE_URL = "https://api.rss2json.com/v1/"

    const val GET_JUST_IN_ARTICLES_URL = "api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml"

    private const val STATUS_OK = "ok"

    const val NO_INTERNET_ACCESS = 0

    const val SERVER_ERROR = 1

    const val UNKNOWN_ERROR = 3

    /**
     * Parse articles from response content
     * @param responseBody response content returned from server
     * @return list of just in articles
     */
    internal fun parseArticles(responseBody: ServerResponse?)
            : List<Article>? = if (responseBody?.status == STATUS_OK) {
        responseBody.items
    } else {
        throw ServerException("Server returned error")
    }

    class ServerException(val error: String) : Throwable()

}




