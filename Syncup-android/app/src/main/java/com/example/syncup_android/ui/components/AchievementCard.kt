package com.example.syncup_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VideogameAsset
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AchievementCard (icon: ImageVector, name: String, score: Int, modifier: Modifier = Modifier){
    Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
        .background(color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(15.dp))
        .padding(10.dp)) {
        Icon(imageVector = icon, contentDescription = null)
        Text(text = name, textAlign = TextAlign.Center, fontSize = 12.sp, modifier = Modifier.padding(top = 5.dp))
        Text(text = score.toString(), style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 5.dp))
    }
}

@Preview
@Composable
fun defaultPreview(){
    AchievementCard(Icons.Outlined.VideogameAsset, name="Points", score = 5)
}