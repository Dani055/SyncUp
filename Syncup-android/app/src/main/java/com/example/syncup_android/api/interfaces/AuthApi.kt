package com.example.syncup_android.api.interfaces

import com.example.syncup_android.api.ApiConstants
import com.example.syncup_android.data.req.SignInRequest
import com.example.syncup_android.data.res.GetLeaderboardResponse
import com.example.syncup_android.data.res.SignInResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


//Builder for the authentication API
interface AuthApi {
    @POST("/auth/signin")
    suspend fun signIn(
            @Body body: SignInRequest
    ) : Response<SignInResponse>
    @GET("/auth/leaderboard")
    suspend fun getLeaderboard(
    ) : Response<GetLeaderboardResponse>

    companion object{

        val instance by lazy {
            Retrofit.Builder().baseUrl(ApiConstants.BASE_URL_BACKEND).addConverterFactory(
                JacksonConverterFactory.create()).build().create(AuthApi::class.java)
        }
    }
}