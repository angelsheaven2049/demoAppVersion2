package com.angelsheaven.demo.data

import com.angelsheaven.demo.data.storage.StorageDataSource
import io.reactivex.Flowable
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class RepositoryTest {

    @Test
    fun `data source returns a value`() {
        val ds = Mockito.mock(StorageDataSource::class.java)
        `when`(ds.retrieveArticleDetail(0)).then {
            Flowable.just(Article(0))
        }

        val repository = Repository(networkDataSource = null, storageDataSource = ds)
        assertNotNull(repository.retrieveArticleDetail(0))
    }




}