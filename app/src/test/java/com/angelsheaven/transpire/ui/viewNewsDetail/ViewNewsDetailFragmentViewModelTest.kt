package com.angelsheaven.transpire.ui.viewNewsDetail

import com.angelsheaven.transpire.data.ArticleRepository
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class ViewNewsDetailFragmentViewModelTest{

    private val repository = mock(ArticleRepository::class.java)
    private val viewNewsDetailViewModel = ViewNewsDetailFragmentViewModel(repository)

    @Test
    fun testNull(){
        assertThat(viewNewsDetailViewModel.articleDetail,notNullValue())
    }
}
