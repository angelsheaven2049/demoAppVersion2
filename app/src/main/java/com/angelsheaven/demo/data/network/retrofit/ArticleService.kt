package com.angelsheaven.demo.data.network.retrofit

import com.angelsheaven.demo.data.network.NetworkContract
import com.angelsheaven.demo.data.network.ServerResponse
import retrofit2.http.GET

interface ArticleService {

    @GET(NetworkContract.GET_JUST_IN_ARTICLES_URL)
    fun getJustInArticle(): ServerResponse

}