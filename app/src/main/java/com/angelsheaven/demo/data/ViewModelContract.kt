package com.angelsheaven.demo.data

import androidx.paging.PagedList

object ViewModelContract {
    val configLoadData = PagedList.Config.Builder()
        .setPageSize(100)
        .setPrefetchDistance(100)
        .setEnablePlaceholders(true)
        .build()
}











