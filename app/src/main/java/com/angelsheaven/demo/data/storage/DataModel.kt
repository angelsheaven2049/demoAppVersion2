package com.angelsheaven.demo.data.storage

import android.text.Html
import android.text.Spanned
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.angelsheaven.demo.data.storage.DatabaseContract.DATE_FORMATTER_PATTERN
import com.angelsheaven.demo.data.storage.DatabaseContract.SERVER_DATE_TIME_PATTERN
import com.angelsheaven.demo.data.storage.DatabaseContract.TABLE_ARTICLE
import com.angelsheaven.demo.data.storage.DatabaseContract.TIME_FORMATTER_PATTERN
import java.text.SimpleDateFormat
import java.util.*

/**
 * Data models for project
 */
data class Enclosure(
    val link: String? = null,
    val type: String? = null,
    val thumbnail: String? = null
) {
    override fun equals(other: Any?): Boolean {
        return ((other is Enclosure)
                && (this.link == other.link
                && this.type == other.type
                && this.thumbnail == other.thumbnail))
    }

    override fun hashCode(): Int {
        var result = link?.hashCode() ?: 0
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        return result
    }
}

@Entity(tableName = TABLE_ARTICLE)
data class Article(
    @PrimaryKey(autoGenerate = true) var roomId: Int = 0,
    val title: String? = "",
    val pubDate: String? = "",
    val link: String? = "",
    val guid: String? = "",
    val author: String? = "",
    val thumbnail: String? = "",
    val description: String? = "",
    val content: String? = "",
    val enclosure: Enclosure? = null,
    val categories: List<String>? = null,
    var topArticle: Boolean = false
) {

    @Ignore
    private val pubDateObject =
        if (!pubDate.isNullOrEmpty()) SimpleDateFormat(SERVER_DATE_TIME_PATTERN,Locale.getDefault()).parse(pubDate) else null

    fun getFormattedPublishDate(): String {
        return if (pubDateObject != null) {
            val formatter = SimpleDateFormat(DATE_FORMATTER_PATTERN, Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateTime = SimpleDateFormat(SERVER_DATE_TIME_PATTERN,Locale.getDefault()).parse(pubDate)
            formatter.format(dateTime).trim()
        } else ""
    }

    fun getFormattedPublishTime(): String {
        return if (pubDateObject != null) {
            val formatter = SimpleDateFormat(TIME_FORMATTER_PATTERN, Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            formatter.format(pubDateObject).replace("am", "AM")
                .replace("pm", "PM").trim()
        } else ""
    }

    fun getFormattedContent(): Spanned? {
        return Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT)
    }


    override fun equals(other: Any?): Boolean {
        return ((other is Article)
                && this.title == other.title
                && this.pubDate == other.pubDate
                && this.link == other.link
                && this.guid == other.guid
                && this.author == other.author
                && this.thumbnail == other.thumbnail
                && this.description == other.description
                && this.content == other.content
                && this.enclosure == other.enclosure
                && this.categories == other.categories)
    }

    fun getThumbnailUrl(): String? {
        return if (topArticle) this.enclosure?.thumbnail else this.thumbnail
    }

    override fun hashCode(): Int {
        var result = roomId
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (pubDate?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (guid?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + (enclosure?.hashCode() ?: 0)
        result = 31 * result + (categories?.hashCode() ?: 0)
        result = 31 * result + topArticle.hashCode()
        result = 31 * result + (pubDateObject?.hashCode() ?: 0)
        return result
    }
}

data class Feed(
    val url: String? = null,
    val title: String? = null,
    val link: String? = null,
    val author: String? = null,
    val description: String? = null,
    val image: String? = null
) {

    override fun equals(other: Any?): Boolean {
        return ((other is Feed)
                && (this.url == other.url
                && this.title == other.title
                && this.link == other.link
                && this.author == other.author
                && this.description == other.description
                && this.image == other.image))
    }

    override fun hashCode(): Int {
        var result = url?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        return result
    }
}

