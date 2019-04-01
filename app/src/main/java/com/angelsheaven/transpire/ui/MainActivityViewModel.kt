package com.angelsheaven.transpire.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelsheaven.transpire.utilities.MyLogger
import javax.inject.Inject

/**
 * This is viewmodel clas for main MainActivity used to control
 * business logic for MainActivity and hold data
 * @author Quan Nguyen
 * {@link ViewModel} for {@link MainActivity}
 */

class MainActivityViewModel @Inject constructor(private val app: Application) : ViewModel(), MyLogger {

    /**
     * requestRefreshData used to notify request fresh data
     * to fragments
     * @see ListArticlesFragment
     */
    val requestRefreshData by lazy { MutableLiveData<Boolean>() }

}

