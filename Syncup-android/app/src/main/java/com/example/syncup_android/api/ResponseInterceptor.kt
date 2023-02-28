package com.example.syncup_android.api

import com.example.syncup_android.api.interfaces.IResponseInterceptor
import org.json.JSONObject
import retrofit2.Response

class ResponseInterceptor: IResponseInterceptor {
    override fun <T: Any>interceptError(response: Response<T>){
        if(!response.isSuccessful){
            val jObjError = JSONObject(response.errorBody()?.string())
            val message = jObjError.get("message").toString()
            throw Exception(message)
        }
    }
}