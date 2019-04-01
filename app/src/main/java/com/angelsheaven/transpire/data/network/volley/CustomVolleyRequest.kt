package com.angelsheaven.volley

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.HttpHeaderParser.parseCacheHeaders
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

/**
 * This class modify Request class of Volley to
 * add more controls such priority for request
 * or timeout, max tries number on each
 * @author Quan Nguyen
 */
class CustomVolleyRequest(
    method: Int,
    url: String?,
    private val listener: Response.Listener<JSONObject>,
    errorListener: Response.ErrorListener?
) : Request<JSONObject>(method, url, errorListener) {

    private val timeOutMs by lazy { DefaultRetryPolicy.DEFAULT_TIMEOUT_MS }
    private val maxRetries by lazy { DefaultRetryPolicy.DEFAULT_MAX_RETRIES }
    private val backOffMult by lazy { DefaultRetryPolicy.DEFAULT_BACKOFF_MULT }

    private val mRetryPolicy by lazy {
        DefaultRetryPolicy(timeOutMs, maxRetries, backOffMult)
    }

    init {
        retryPolicy = mRetryPolicy
    }

    private var mPriority: Priority? = null

    override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject>? {
        try {
            response?.run {

                val jsonString = String(
                    response.data,
                    Charset.forName(HttpHeaderParser.parseCharset(response.headers))
                )

                return Response.success(
                    JSONObject(jsonString)
                    , parseCacheHeaders(response)
                )
            }

        } catch (ex: JSONException) {
            return Response.error(ParseError(ex))
        }

        return null
    }

    override fun deliverResponse(response: JSONObject?) {
        listener.onResponse(response)
    }

    /**
     * Set priority for request 
     * @param priority priority to set
     */
    fun setPriority(priority: Priority) {
        this.mPriority = priority
    }

    override fun getPriority(): Priority {
        return mPriority ?: Priority.NORMAL
    }

}
