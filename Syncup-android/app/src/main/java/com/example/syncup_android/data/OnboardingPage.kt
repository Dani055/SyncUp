package com.example.syncup_android.data

import androidx.annotation.DrawableRes
import com.example.syncup_android.R

data class OnboardingPage(val title: String, val description: String, @DrawableRes val image: Int)

val onboardPages = listOf(
    OnboardingPage(title = "Build", description = "Build better relationships with your colleagues", R.drawable.first_onboarding),
    OnboardingPage(title = "Collaborate", description = "Collaborate with others and have fun while getting work done", R.drawable.second_onboarding),
    OnboardingPage(title = "Communicate", description = "Improve communication between remote teams", R.drawable.third_onboarding)
)