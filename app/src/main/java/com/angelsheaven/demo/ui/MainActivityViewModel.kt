package com.angelsheaven.demo.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import com.angelsheaven.demo.utilities.MyLogger
import javax.inject.Inject

/**
 * This is viewmodel clas for main MainActivity used to control
 * business logic for MainActivity and hold data
 * @author Quan Nguyen
 * {@link ViewModel} for {@link MainActivity}
 */

class MainActivityViewModel @Inject constructor(private val app: Application) : ViewModel(), MyLogger

