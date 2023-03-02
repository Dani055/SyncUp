package com.example.syncup_android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VideogameAsset
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.syncup_android.data.UserContext
import com.example.syncup_android.data.model.User

@Composable
fun LeaderboardCard (position: Int, user: User){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .background(shape = RoundedCornerShape(15.dp), color = MaterialTheme.colorScheme.onPrimary)
        .padding(horizontal = 15.dp, vertical = 10.dp)){
        Text(style = MaterialTheme.typography.titleMedium, text = position.toString())
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 18.dp).weight(1f)) {
            Image(contentScale = ContentScale.Crop, modifier = Modifier
                .size(30.dp)
                .clip(CircleShape), painter = rememberAsyncImagePainter(user.profileImageUrl), contentDescription = null)
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(style = MaterialTheme.typography.titleSmall, text = "${user.firstName} ${user.lastName} ${if (UserContext.loggedUser?.id == user.id) "(You)" else ""}")
                Text(fontSize = 12.sp, text = "${user.position}")
            }
        }
        Text(style = MaterialTheme.typography.titleSmall, text = "${user.points}p")
    }
}

@Preview
@Composable
fun LeaderboardCardPreview(){
    LeaderboardCard(position = 3, User("123", "email@email.com", "Some", "Full-stack", "One", "123asd", 120, listOf("a")))
}