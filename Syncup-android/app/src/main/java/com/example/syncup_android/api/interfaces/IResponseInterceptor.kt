package com.example.syncup_android.api.interfaces

import retrofit2.Response

interface IResponseInterceptor {
    fun <T: Any>interceptError(response: Response<T>)
}