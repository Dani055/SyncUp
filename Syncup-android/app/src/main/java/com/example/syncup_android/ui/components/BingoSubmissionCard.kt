package com.example.syncup_android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.syncup_android.data.model.Submission

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BingoSubmissionCard(submission: Submission, index: Int) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape),
                    model = submission.completedBy.profileImageUrl,
                    contentDescription = null
                )
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "${submission.completedBy.firstName} ${submission.completedBy.lastName}"
                )
                Text(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp,
                    text = "${index + 2} hours ago"
                )
            }
            Text(
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 15.dp),
                text = "Task: ${submission.activity.name}"
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                model = submission.evidenceUrl,
                contentDescription = submission.activity.description
            )
        }

    }
}
