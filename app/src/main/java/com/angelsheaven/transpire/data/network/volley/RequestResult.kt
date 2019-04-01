package com.angelsheaven.volley

/**
 * Returned object for each request to server
 */
sealed class RequestResult {
    class ReturnedData<T>(val data: T) : RequestResult()
    class ReturnedError(val error: Int) : RequestResult()
}
