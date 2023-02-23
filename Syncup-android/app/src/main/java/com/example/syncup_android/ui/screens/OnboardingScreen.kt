package com.example.syncup_android.ui.screens

import android.graphics.Outline
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncup_android.R
import com.example.syncup_android.data.onboardPages
import com.example.syncup_android.ui.components.OnboardingPageUI
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen (onGetStartedClick: () -> Unit, modifier: Modifier = Modifier){
    val pagerState = rememberPagerState(initialPage = 0);
    val scope = rememberCoroutineScope();

    Column(modifier = modifier.fillMaxSize()) {
        Image(modifier = Modifier.fillMaxWidth(),contentScale = ContentScale.Crop, painter = painterResource(R.drawable.onboardingbg), contentDescription = null)
        HorizontalPager(count = 3, state = pagerState, modifier = Modifier.fillMaxWidth()) {
            OnboardingPageUI(onboardingPage = onboardPages[it])
        }
        Spacer(Modifier.weight(1f))
        HorizontalPagerIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), pagerState = pagerState, activeColor = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.weight(1f))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp, bottom = 40.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            AnimatedVisibility(enter = fadeIn(), exit = fadeOut(), visible = pagerState.currentPage < 2) {
                ClickableText(style = TextStyle(color = MaterialTheme.colorScheme.primary), text = AnnotatedString("Skip"), onClick = {onGetStartedClick()})
            }
            AnimatedVisibility(enter = fadeIn(), exit = fadeOut(), visible = pagerState.currentPage < 2) {
                ClickableText(style = TextStyle(color = MaterialTheme.colorScheme.primary), text = AnnotatedString("Next"), onClick = {scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1)} })
            }
            AnimatedVisibility(enter = fadeIn(), exit = fadeOut(), visible = pagerState.currentPage == 2) {
                OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = {onGetStartedClick()}){
                    Text(color = MaterialTheme.colorScheme.primary, text = "Get started")
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    OnboardingScreen(onGetStartedClick = {})
}