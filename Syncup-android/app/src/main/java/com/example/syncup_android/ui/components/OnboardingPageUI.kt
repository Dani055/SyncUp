package com.example.syncup_android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.syncup_android.R
import com.example.syncup_android.data.OnboardingPage

@Composable
fun OnboardingPageUI(onboardingPage: OnboardingPage) {
    Column(modifier = Modifier.padding(horizontal = 50.dp),horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = onboardingPage.image), contentDescription = null)
        Text(color = MaterialTheme.colorScheme.primary, text = onboardingPage.description)
    }
}