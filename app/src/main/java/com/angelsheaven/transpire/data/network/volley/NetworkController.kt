package com.angelsheaven.volley

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.angelsheaven.transpire.testing.OpenForTesting
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class is used to control each network request
 * going out, for example, get data or download image
 * @sample ImageRequester, NetworkDataSource
 */
@OpenForTesting
@Singleton
class NetworkController @Inject constructor(app: Application) {

    companion object {
        private var mInstance: NetworkController? = null
        fun getInstance() = mInstance
    }

    private val cache by lazy { DiskBasedCache(app.cacheDir, 1024 * 1024) }

    private val network by lazy { BasicNetwork(HurlStack()) }

    private val mRequestQueue: RequestQueue
            by lazy { RequestQueue(cache, network) }

    init {
        mInstance = this
        mRequestQueue.start()
    }

    fun addToRequestQueue(req: Request<JSONObject>) {
        mRequestQueue.add(req)
    }

    fun getRequestQueue(): RequestQueue {
        return mRequestQueue
    }

}
