/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.angelsheaven.demo.utilities

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * BindingAdapters class - Utility class used to
 * add more handy attributes for UI Widget
 * @author Quan Nguyen
 * @see fragment_view_article_detail in res/layout
 */
object BindingAdapters {

    /**
     * Hide a view if input inputText is empty
     * @param view need to check
     * @inputText input inputText needed to check
     */
    @BindingAdapter("app:hideIfEmpty")
    @JvmStatic fun hideIfEmpty(view: View, inputText:String?){
        view.visibility = if(inputText.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

}