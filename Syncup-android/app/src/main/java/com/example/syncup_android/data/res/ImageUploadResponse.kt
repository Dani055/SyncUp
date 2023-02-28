package com.example.syncup_android.data.res

import com.fasterxml.jackson.annotation.JsonProperty

data class ImageUploadResponse (
    @JsonProperty("message")
    val message: String,
    @JsonProperty("links")
    val links: List<String>
)