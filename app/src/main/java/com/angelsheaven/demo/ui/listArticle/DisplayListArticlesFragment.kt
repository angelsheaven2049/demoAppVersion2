package com.angelsheaven.demo.ui.listArticle


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.angelsheaven.demo.R
import com.angelsheaven.demo.di.Injectable
import com.angelsheaven.demo.ui.MainActivity
import com.angelsheaven.demo.utilities.MyLogger
import com.angelsheaven.demo.utilities.mySnackBar
import com.angelsheaven.demo.widgets.ItemDecoration
import kotlinx.android.synthetic.main.fragment_display_list_articles.*
import javax.inject.Inject

/**
 * This class used to display just in articles
 * @author Quan Nguyen
 */
class DisplayListArticlesFragment : Fragment(), Injectable, MyLogger {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * Declare fields for class
     */
    /**
     * mViewModel hold data and conduct retrieve
     * list of just in articles
     */
    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DisplayListArticlesFragmentViewModel::class.java)
    }

    /**
     * Lambda function to handle user click event
     * on a specific article of article listview
     */
    private val onUserClickItem: (Int) -> Unit = { newsId ->
        val newsDetailsScreen =
            DisplayListArticlesFragmentDirections.moveFromListNewsToViewNewsDetail(
                newsId
            )

        if (findNavController().currentDestination?.id == R.id.list_article_dest)
            findNavController().navigate(newsDetailsScreen)
    }

    /**
     * Article adapter to hold articles list
     */
    private lateinit var mArticlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Register a listener to handle request refreshingd ata
         * from mainactivity
         */
        val mainActivityViewModel = (activity as MainActivity).mViewModel
        mainActivityViewModel.requestRefreshData.observe(this, Observer { requested ->
            if (requested)
                mViewModel.loadArticles()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_list_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lv_article_items.setHasFixedSize(true)

        /**
         * Init adapter for list of articles
         */
        initAdapter()

        /**
         * Adding space between each rows in
         * articles listview
         */
        val padding = resources.getDimensionPixelSize(R.dimen.articleSpacing)

        lv_article_items.addItemDecoration(ItemDecoration(padding))

        /**
         * Register a listener to handle
         * swipe to refresh event
         */
        swipe_refresh.setOnRefreshListener {
            mViewModel.loadArticles()
        }

        /**
         * A listener to handle event finishing loading data
         */
        mViewModel.isCompleteReloadingData.observe(this, Observer {
            swipe_refresh.isRefreshing = false
        })

    }

    /**
     * Init article adapter
     */
    private fun initAdapter() {
        mArticlesAdapter = ArticlesAdapter(onUserClickItem)

        lv_article_items.adapter = mArticlesAdapter

        mViewModel
            .articles.observe(this, Observer {
            mArticlesAdapter.submitList(it)
        })

        mViewModel
            .networkErrors.observe(this, Observer { errorCode ->
            val errorMessageToUser = mViewModel.getErrorMessage(errorCode)
            errorCode?.run {
                this@DisplayListArticlesFragment.view?.let { view ->
                    view.mySnackBar(
                        errorMessageToUser
                    ).show()

                    swipe_refresh.isRefreshing = false
                    mViewModel.notifyNetworkErrorDisplayed()
                }

            }
        })
    }

}

