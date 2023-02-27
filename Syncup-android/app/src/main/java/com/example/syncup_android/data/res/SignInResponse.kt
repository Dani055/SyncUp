package com.example.syncup_android.data.res

import com.example.syncup_android.data.User
import com.fasterxml.jackson.annotation.JsonProperty

data class SignInResponse(
    @JsonProperty("message")
    val message: String,
    @JsonProperty("user")
    val user: User
)
