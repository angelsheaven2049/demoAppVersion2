package com.angelsheaven.demo.data.storage

import com.angelsheaven.demo.data.Article
import com.angelsheaven.demo.data.Repository
import io.reactivex.Flowable
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class StorageDataSourceTest {

    @Test
    fun `data source returns a value`() {
        val ds = mock(StorageDataSource::class.java)
        `when`(ds.retrieveArticleDetail(0)).then {
             Flowable.just(Article(0))
        }

        val repository = Repository(networkDataSource = null, storageDataSource = ds)
        assertNotNull(repository.retrieveArticleDetail(0))
    }


}
