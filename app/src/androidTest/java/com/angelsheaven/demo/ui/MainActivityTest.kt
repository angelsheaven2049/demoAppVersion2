package com.angelsheaven.demo.ui

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.angelsheaven.demo.R
import com.angelsheaven.demo.ui.listArticle.ArticlesAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random


/**
 * This class used to test UI of app
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private val pendingTimeToLoadData = 1000L
    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var mActivity: MainActivity
    private var articleCount: Int? = null
    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            mActivity = it
            val articleList = mActivity.findViewById<RecyclerView>(R.id.lv_article_items)
            articleCount = articleList.adapter?.itemCount ?: 0
        }
    }

    @Test
    fun testTopArticleExistIfThereIsMoreThanOneItemOnArticleList() {
        Thread.sleep(pendingTimeToLoadData)

        articleCount?.run {
            if (this > 0) {
                onView(withId(R.id.parent_layout_top_article))
                    .check(matches(isCompletelyDisplayed()))
            }
        }

    }

    @Test
    fun testPressOnRandomArticleOnList() {

        Thread.sleep(pendingTimeToLoadData)

        articleCount?.run {
            if (this > 0) {
                val randomArticleIndex = Random(this - 1).nextInt(Integer.SIZE - 1)
                onView(withId(R.id.lv_article_items))
                    .perform(
                        RecyclerViewActions.actionOnItemAtPosition<ArticlesAdapter.ArticlesViewHolder>
                            (randomArticleIndex, click())
                    )

                onView(withId(R.id.parent_layout_view_item_detail))
                    .check(matches(isDisplayed()))
            }
        }

    }

    @Test
    fun testSameTitleAfterUserPressOnASpecificArticle() {

        Thread.sleep(pendingTimeToLoadData)

        articleCount?.run {
            if (this > 0) {
                val randomArticleIndex = Random(this - 1).nextInt(Integer.SIZE - 1)
                Log.d(MainActivityTest::class.java.simpleName, "$this-$randomArticleIndex")

                val topArticleView = mActivity.findViewById<View>(R.id.parent_layout_top_article)

                val textViewTopArticleView = topArticleView.findViewById<TextView>(R.id.tv_article_title)

                val title = textViewTopArticleView.text

                onView(withId(R.id.parent_layout_top_article))
                    .perform(click())

                Thread.sleep(pendingTimeToLoadData)

                onView(withId(R.id.tv_title_detail_fragment))
                    .check(matches(withText(title.toString())))
            }
        }
    }

    @Test
    fun testToolbarTitleMatchDisplayArticleListScree() {
        Thread.sleep(pendingTimeToLoadData)

        onView(withText(R.string.labelListArticleScreen))
            .check(matches(isDisplayed()))

    }

    @Test
    fun testToolbarTitleMatchViewArticleDetailTitle() {
        Thread.sleep(pendingTimeToLoadData)

        articleCount?.run {
            if (this > 0) {
                val randomArticleIndex = Random(this - 1).nextInt(Integer.SIZE - 1)
                onView(withId(R.id.lv_article_items))
                    .perform(
                        RecyclerViewActions.actionOnItemAtPosition<ArticlesAdapter.ArticlesViewHolder>
                            (randomArticleIndex, click())
                    )

                Thread.sleep(pendingTimeToLoadData)

                onView(withText(R.string.labelViewArticleDetailDest))
                    .check(matches(isDisplayed()))
            }
        }
    }


}