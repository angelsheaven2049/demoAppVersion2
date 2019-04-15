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

package com.angelsheaven.demo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angelsheaven.demo.ui.MainActivityViewModel
import com.angelsheaven.demo.ui.listArticle.DisplayListArticlesFragmentViewModel
import com.angelsheaven.demo.ui.viewNewsDetail.ViewNewsDetailFragmentViewModel
import com.angelsheaven.demo.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKeyJava(DisplayListArticlesFragmentViewModel::class)
    abstract fun bindUserViewModel(displayListArticlesFragmentViewModel: DisplayListArticlesFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKeyJava(ViewNewsDetailFragmentViewModel::class)
    abstract fun bindSearchViewModel(viewNewsDetailFragmentViewModel: ViewNewsDetailFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKeyJava(MainActivityViewModel::class)
    abstract fun bindRepoViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
