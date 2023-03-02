package com.example.syncup_android.data.res

import com.example.syncup_android.data.model.Submission
import com.fasterxml.jackson.annotation.JsonProperty

data class GetSubmissionsForBingoResponse(
    @JsonProperty("message")
    val message: String,
    @JsonProperty("submissions")
    val submissions: List<Submission>
)
