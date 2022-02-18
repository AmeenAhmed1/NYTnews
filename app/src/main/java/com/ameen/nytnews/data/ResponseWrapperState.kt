package com.ameen.nytnews.data

sealed class ResponseWrapperState<T>(
    val responseData: T? = null,
    val responseMessage: String? = null
) {
    class Loading<T> : ResponseWrapperState<T>()
    class Success<T>(data: T) : ResponseWrapperState<T>(data)
    class Error<T>(message: String?) : ResponseWrapperState<T>(responseMessage = message)
}