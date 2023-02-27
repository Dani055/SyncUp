package com.example.syncup_android.api

import com.example.syncup_android.data.req.SignInRequest
import com.example.syncup_android.data.res.SignInResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {
    @POST("/auth/signin")
    suspend fun signIn(
            @Body body: SignInRequest
    ) : Response<SignInResponse>

    companion object{
        val client = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val request = chain.request()
                    val response = chain.proceed(request)
                    if(!response.isSuccessful){
                        val jObjError = JSONObject(response.body?.string())
                        val message = jObjError.get("message").toString()
                        return response.newBuilder()
                            .body((response.body?.string() ?: "").toResponseBody(response.body?.contentType()))
                            .build()
                    }

                    // Return a new response with the modified body
                    return response.newBuilder()
                        .body((response.body?.string() ?: "").toResponseBody(response.body?.contentType()))
                        .build()
                }
            })
            .build()

        val instance by lazy {
            Retrofit.Builder().baseUrl(ApiConstants.BASE_URL).addConverterFactory(
                JacksonConverterFactory.create()).build().create(AuthApi::class.java)
        }
    }
}