package com.test.testproject.domain.remote

sealed class RequestResult {
    class OnSuccess<T>(val data: List<T>) : RequestResult()
    class OnError(val exception: Exception) : RequestResult()
}
