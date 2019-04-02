package com.angelsheaven.demo.data.storage

/**
 * an contract object defines parameters to manipulate
 * on local database (initialization, insert query, delete data)
 */
object DatabaseContract{
    const val DATABASE_NAME = "transpire.db"

    const val TABLE_ARTICLE = "article"

    const val SELECT_ARTICLE_DETAIL = "SELECT * FROM $TABLE_ARTICLE WHERE roomId = %d"

    const val DATE_FORMATTER_PATTERN = "MMMM dd, yyyy"

    const val TIME_FORMATTER_PATTERN = "hh:MM a"

    const val SERVER_DATE_TIME_PATTERN = "yyyy-MM-dd hh:MM:ss"

    const val GET_ARTICLES = "SELECT * FROM $TABLE_ARTICLE"

    const val DELETE_ALL_ARTICLES = "DELETE FROM $TABLE_ARTICLE"
}



