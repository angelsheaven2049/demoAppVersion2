package com.angelsheaven.demo.data.storage

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class StorageDataSourceTest {

    lateinit var ds:StorageDataSource

    @Before
    fun setUp() {
        ds = mock(StorageDataSource::class.java)
    }

    @Test
    fun retrieveArticleDetail() {

    }

    @Test
    fun deleteAllArticles() {
    }

    @Test
    fun insertBulkArticles() {
    }

    @Test
    fun retrieveAllArticles() {
    }
}