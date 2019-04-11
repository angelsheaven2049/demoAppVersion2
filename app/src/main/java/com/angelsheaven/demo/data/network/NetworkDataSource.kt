package com.angelsheaven.demo.data.network

import com.android.volley.NoConnectionError
import com.android.volley.Request
import com.android.volley.Response
import com.angelsheaven.demo.data.network.volley.CustomVolleyRequest
import com.angelsheaven.demo.data.network.volley.NetworkController
import com.angelsheaven.demo.data.network.volley.RequestResult
import com.angelsheaven.demo.testing.OpenForTesting
import com.angelsheaven.demo.utilities.MyLogger
import com.google.gson.Gson
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
    private val volleyController: NetworkController
) : MyLogger {

    /**
     * Retrieve just in news from server
     * @return RequestResult the result of request data
     */
    suspend fun getJustInNewsAsync() = suspendCoroutine<RequestResult?> { cont ->

        val url = NetworkContract.BASE_URL + NetworkContract.GET_JUST_IN_ARTICLES_URL

        val request = CustomVolleyRequest(
            Request.Method.GET,
            url
            , Response.Listener {
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

            }
            , Response.ErrorListener { requestError ->

                val errorNotificationToUser: Int = when (requestError) {
                    is NoConnectionError -> NetworkContract.NO_INTERNET_ACCESS
                    else -> NetworkContract.SERVER_ERROR
                }

                val volleyRequestResult = RequestResult
                    .ReturnedError(errorNotificationToUser)

                cont.resume(volleyRequestResult)

            })

        request.priority = Request.Priority.HIGH

        volleyController
            .addToRequestQueue(request)

    }
}

