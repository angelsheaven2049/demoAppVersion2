package com.angelsheaven.demo.data.network

import com.angelsheaven.demo.data.network.retrofit.ArticleService
import com.angelsheaven.demo.data.network.volley.RequestResult
import com.angelsheaven.demo.testing.OpenForTesting
import com.angelsheaven.demo.utilities.MyLogger
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


/**
 * This class used to handle data request to server
 * @author Quan Nguyen
 */
@OpenForTesting
@Singleton
class NetworkDataSource @Inject constructor(
    private val articleService: ArticleService
) : MyLogger {

    /**
     * Retrieve just in news from server
     * @return RequestResult the result of request data
     */
    suspend fun getJustInNewsAsync() = suspendCoroutine<RequestResult> { cont ->
        val requestArticle = articleService.getJustInArticle()

        requestArticle.enqueue(object : Callback<ServerResponse> {

            lateinit var volleyRequestResult: RequestResult

            override fun onResponse(
                call: Call<ServerResponse>,
                response: retrofit2.Response<ServerResponse>
            ) {

                if (response.isSuccessful) {
                    val serverResponse = response.body()

                    volleyRequestResult = RequestResult
                        .ReturnedData(serverResponse)

                    cont.resume(volleyRequestResult)
                } else {
                    volleyRequestResult = RequestResult
                        .ReturnedError(NetworkContract.SERVER_ERROR)
                }

            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                volleyRequestResult = RequestResult.ReturnedError(NetworkContract.UNKNOWN_ERROR)
            }
        })

        log(requestArticle.toString())


    }
}

