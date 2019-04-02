package com.angelsheaven.demo.ui.viewNewsDetail

import com.angelsheaven.demo.data.Repository
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class ViewNewsDetailFragmentViewModelTest{

    private val repository = mock(Repository::class.java)
    private val viewNewsDetailViewModel = ViewNewsDetailFragmentViewModel(repository)

    @Test
    fun testNull(){
        assertThat(viewNewsDetailViewModel.articleDetail,notNullValue())
    }
}
