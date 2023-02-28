package com.example.syncup_android.api.repository

import com.example.syncup_android.api.ResponseInterceptor
import com.example.syncup_android.api.interfaces.AuthApi
import com.example.syncup_android.data.res.SignInResponse
import com.example.syncup_android.data.req.SignInRequest
import com.example.syncup_android.data.res.GetLeaderboardResponse
import org.json.JSONObject
import retrofit2.Response

class AuthRepository (private val resInterceptor: ResponseInterceptor = ResponseInterceptor()){
    suspend fun login(username: String) : SignInResponse {
        val response = AuthApi.instance.signIn(SignInRequest(username))
        resInterceptor.interceptError(response)
        return response.body()!!
    }
    suspend fun getLeaderboard() : GetLeaderboardResponse {
        val response = AuthApi.instance.getLeaderboard()
        resInterceptor.interceptError(response)
        return response.body()!!
    }

}