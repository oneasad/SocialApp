package com.oneasad.socialapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.oneasad.socialapp.data.ApiPost
import com.oneasad.socialapp.data.local.LocalPost

@Composable
fun PostScreen(
    post: LocalPost,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = post.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = post.body, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { onEditClick(post.id) }) {
                Text("Edit")
            }

            Button(
                onClick = { onDeleteClick(post.id) },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("Delete")
            }
        }
    }
}


@Composable
fun PostItem(post: LocalPost, onPostClick: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPostClick(post.id) }
            .padding(8.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ){
            Text(text = post.title, fontWeight = FontWeight.Bold)
            HorizontalDivider(
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(text = post.body, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

//@Preview
//@Composable
//fun PreviewPostScreen() {
//    val post = Post(
//        title = "Title 1", body = "Body 1",
//        userId = 1,
//        id = 1
//    )
////    PostItem(
////        post = post,
////        onPostClick = { }
////    )
//    PostScreen(post = post)
//}