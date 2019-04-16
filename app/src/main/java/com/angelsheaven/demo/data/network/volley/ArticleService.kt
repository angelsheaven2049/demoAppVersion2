package com.angelsheaven.demo.data.network.volley

import com.android.volley.NoConnectionError
import com.android.volley.Request
import com.android.volley.Response
import com.angelsheaven.demo.data.network.NetworkContract
import com.angelsheaven.demo.data.network.ServerResponse
import com.angelsheaven.demo.utilities.MyLogger
import com.google.gson.Gson
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ArticleService(
    private val baseUrl: String,
    private val volleyController: NetworkController
) : MyLogger {

    suspend fun getJustInNewsAsync() = suspendCoroutine<RequestResult?> { cont ->

        val url = baseUrl + NetworkContract.GET_JUST_IN_ARTICLES_URL

        val request = CustomVolleyRequest(
            Request.Method.GET,
            url,
            Request.Priority.HIGH,
            Response.Listener {
                try {

                    val serverResponse = Gson().fromJson(it.toString(), ServerResponse::class.java)

                    log(serverResponse.toString())

                    val volleyRequestResult = RequestResult
                        .ReturnedData(serverResponse)

                    cont.resume(volleyRequestResult)

                } catch (ex: Exception) {
                    ex.printStackTrace()
                    val volleyRequestResult = RequestResult
                        .ReturnedError(NetworkContract.UNKNOWN_ERROR)
                    cont.resume(volleyRequestResult)
                }

            },
            Response.ErrorListener { requestError ->

                val errorNotificationToUser: Int = when (requestError) {
                    is NoConnectionError -> NetworkContract.NO_INTERNET_ACCESS
                    else -> NetworkContract.SERVER_ERROR
                }

                val volleyRequestResult = RequestResult
                    .ReturnedError(errorNotificationToUser)

                cont.resume(volleyRequestResult)

            })

        volleyController
            .addToRequestQueue(request)

    }
}