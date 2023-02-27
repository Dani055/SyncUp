package com.example.syncup_android.api

import com.example.syncup_android.data.res.SignInResponse
import com.example.syncup_android.data.req.SignInRequest
import org.json.JSONObject
import retrofit2.Response

class AuthRepository {
    suspend fun login(username: String) : SignInResponse {
        val response = AuthApi.instance.signIn(SignInRequest(username))
        interceptError(response)
        return response.body()!!
    }
    suspend fun <T: Any>interceptError(response: Response<T>){
        if(!response.isSuccessful){
            val jObjError = JSONObject(response.errorBody()?.string())
            val message = jObjError.get("message").toString()
            throw Exception(message)
        }
    }
}