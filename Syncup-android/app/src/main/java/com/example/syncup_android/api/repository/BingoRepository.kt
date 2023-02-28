package com.example.syncup_android.api.repository


import com.example.syncup_android.api.ResponseInterceptor
import com.example.syncup_android.api.interfaces.BingoApi
import com.example.syncup_android.data.req.CreateSubmissionRequest
import com.example.syncup_android.data.res.CreateSubmissionResponse
import com.example.syncup_android.data.res.GetActivitiesResponse
import com.example.syncup_android.data.res.GetActivityByIdResponse
import com.example.syncup_android.data.res.GetSubmissionsForActivityResponse
import org.json.JSONObject
import retrofit2.Response

class BingoRepository(private val resInterceptor: ResponseInterceptor = ResponseInterceptor()){
    suspend fun getActivities() : GetActivitiesResponse {
        val response = BingoApi.instance.getActivities()
        resInterceptor.interceptError(response)
        return response.body()!!
    }
    suspend fun getSubmissionsForActivity(activityId: String) : GetSubmissionsForActivityResponse {
        val response = BingoApi.instance.getSubmissionsForActivity(activityId)
        resInterceptor.interceptError(response)
        return response.body()!!
    }
    suspend fun getActivityById(activityId: String) : GetActivityByIdResponse {
        val response = BingoApi.instance.getActivityById(activityId)
        resInterceptor.interceptError(response)
        return response.body()!!
    }
    suspend fun createSubmission(req: CreateSubmissionRequest) : CreateSubmissionResponse {
        val response = BingoApi.instance.createSubmission(req)
        resInterceptor.interceptError(response)
        return response.body()!!
    }
}