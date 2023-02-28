package com.example.syncup_android.api.repository

import com.example.syncup_android.api.ResponseInterceptor
import com.example.syncup_android.api.interfaces.ImageUploadApi
import com.example.syncup_android.data.res.ImageUploadResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.Response
import java.io.File

class ImageUploadRepository(private val resInterceptor: ResponseInterceptor = ResponseInterceptor()) {
    suspend fun uploadImage(file: File) : ImageUploadResponse {
        val res =  ImageUploadApi.instance.uploadImage(image = MultipartBody.Part.createFormData(
            name = "photo",
            file.name,
            file.asRequestBody(contentType = "image/${file.extension}".toMediaTypeOrNull())))
        resInterceptor.interceptError(res)
        return res.body()!!
    }

}