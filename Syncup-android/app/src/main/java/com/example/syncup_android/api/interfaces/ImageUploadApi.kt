package com.example.syncup_android.api.interfaces

import com.example.syncup_android.api.ApiConstants
import com.example.syncup_android.data.res.ImageUploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageUploadApi {
    @Multipart
    @POST("uploadimg")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ) : Response<ImageUploadResponse>

    companion object{
        val instance by lazy {
            Retrofit.Builder().baseUrl(ApiConstants.BASE_URL_IMAGESERVER).addConverterFactory(
                JacksonConverterFactory.create()).build().create(ImageUploadApi::class.java)
        }
    }
}