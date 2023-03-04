package com.example.syncup_android.api.interfaces

import com.example.syncup_android.api.ApiConstants
import com.example.syncup_android.data.UserContext
import com.example.syncup_android.data.req.CreateSubmissionRequest
import com.example.syncup_android.data.res.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BingoApi {
    @GET("/feed/activities")
    suspend fun getActivities(
    ) : Response<GetActivitiesResponse>

    @GET("/feed/bingo/submissions")
    suspend fun getSubmissionsForActivity(
    ) : Response<GetSubmissionsForBingoResponse>

    @GET("/feed/submission/mine")
    suspend fun getSubmissionsForLoggedUser(
    ) : Response<GetSubmissionsForBingoResponse>

    @POST("/feed/submission")
    suspend fun createSubmission(
        @Body body: CreateSubmissionRequest
    ) : Response<CreateSubmissionResponse>

    @GET("/feed/activities/{activityId}")
    suspend fun getActivityById(
        @Path("activityId") activityId: String
    ) : Response<GetActivityByIdResponse>

    companion object{
        val client = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val originalRequest = chain.request()
                    if(UserContext.loggedUser != null){
                        val newRequest = originalRequest.newBuilder()
                            .header("Authorization", "Bearer ${UserContext.loggedUser!!.id}")
                            .build()
                        return chain.proceed(newRequest)
                    }
                    else{
                        return chain.proceed(originalRequest)
                    }

                }
            })
            .build()

        val instance by lazy {
            Retrofit.Builder().baseUrl(ApiConstants.BASE_URL_BACKEND).client(client).addConverterFactory(
                JacksonConverterFactory.create()).build().create(BingoApi::class.java)
        }
    }

}