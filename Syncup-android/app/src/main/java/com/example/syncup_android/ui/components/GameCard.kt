package com.example.syncup_android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.syncup_android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard (gameName: String, @DrawableRes image: Int, lastUpdated: String, description: String, onPlayClicked: () -> Unit){
    Card(modifier = Modifier.padding(bottom = 20.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 10.dp, end = 20.dp, bottom = 10.dp), verticalAlignment = Alignment.CenterVertically){
            Image(modifier = Modifier.weight(2f), painter = painterResource(image), contentDescription = null)
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(3f)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(style = MaterialTheme.typography.titleMedium, text = gameName)
                    Text(fontSize = 9.sp, text = lastUpdated)
                }
                Text(style = MaterialTheme.typography.bodyMedium, text = description)
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Box{
                        AsyncImage(contentScale = ContentScale.Crop, modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape), model = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8cmFuZG9tJTIwcGVvcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60", contentDescription = null)
                        AsyncImage(contentScale = ContentScale.Crop, modifier = Modifier
                            .size(22.dp)
                            .offset(x = 12.dp)
                            .clip(CircleShape), model = "https://images.unsplash.com/photo-1485206412256-701ccc5b93ca?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTV8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", contentDescription = null)
                        AsyncImage(contentScale = ContentScale.Crop, modifier = Modifier
                            .size(22.dp)
                            .offset(x = 24.dp)
                            .clip(CircleShape), model = "https://images.unsplash.com/photo-1487222477894-8943e31ef7b2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjR8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", contentDescription = null)
                        AsyncImage(contentScale = ContentScale.Crop, modifier = Modifier
                            .size(22.dp)
                            .offset(x = 38.dp)
                            .clip(CircleShape), model = "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", contentDescription = null)
                        AsyncImage(contentScale = ContentScale.Crop, modifier = Modifier
                            .size(22.dp)
                            .offset(x = 48.dp)
                            .clip(CircleShape), model = "https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTZ8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", contentDescription = null)

                    }
                    Button(modifier = Modifier.defaultMinSize(95.dp, 27.dp), contentPadding = PaddingValues(2.dp), onClick = {onPlayClicked()}) {
                        Text(text = "Continue" , fontSize = 12.sp)
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    GameCard("House bingo", R.drawable.first_bingo, "Last updated 1 hour ago", "Compete with your colleagues and earn points. Let’s see who can win this game.", onPlayClicked = {})
}