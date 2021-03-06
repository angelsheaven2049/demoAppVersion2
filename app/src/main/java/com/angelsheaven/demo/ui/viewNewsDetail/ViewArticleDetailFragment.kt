package com.angelsheaven.demo.ui.viewNewsDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.angelsheaven.demo.R
import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.databinding.FragmentViewArticleDetailBinding
import com.angelsheaven.demo.di.Injectable
import com.angelsheaven.demo.utilities.MyLogger
import com.angelsheaven.demo.utilities.mySnackBar
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewArticleDetailFragment class :
 * used to display article's detail
 * @author Quan Nguyen
 */
class ViewArticleDetailFragment : Fragment(), Injectable, MyLogger {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val disposable = CompositeDisposable()

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ViewNewsDetailFragmentViewModel::class.java)
    }

    private lateinit var mBinding: FragmentViewArticleDetailBinding
    private var mArticleId: Int? = null
    private var mObservableDetail: Flowable<Article>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentViewArticleDetailBinding.inflate(inflater, container, false)

        mBinding.viewmodel = viewModel

        arguments?.let { bundle ->
            /**
             * Retrieve the articleId which is sent from
             * list article fragment
             */
            val safeArgs = ViewArticleDetailFragmentArgs.fromBundle(bundle)
            mArticleId = safeArgs.articleId

            mArticleId?.run {
                /**
                 * Query article detail from local database
                 */
                mObservableDetail = viewModel.getArticleDetailObservable(this)

                mObservableDetail?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({ returnedNewsDetail ->
                        mBinding.viewmodel?.articleDetail?.set(returnedNewsDetail)
                    }, { error ->
                        log("Unable to get article detail ${error.message}")
                        view?.mySnackBar(
                            getString(R.string.unable_to_get_article)
                        )?.show()
                    })
                    ?.let {
                        disposable.add(
                            it
                        )
                    }
            }

        }

        return mBinding.root
    }

    override fun onPause() {
        super.onPause()
        mArticleId?.run { mObservableDetail?.unsubscribeOn(Schedulers.io()) }
    }

}
