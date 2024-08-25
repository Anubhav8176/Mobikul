package com.anucodes.webkulassignment.screenthree

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.webkulassignment.Model.CommentModel


@Composable
fun CommentItem(comment: CommentModel){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp, 5.dp)
    ) {
        Text(text = comment.name,
            fontSize = 22.sp,
            modifier = Modifier.padding(8.dp))
        Spacer(modifier = Modifier.size(10.dp))

        Text(text = comment.body,
            fontSize = 16.sp,
            modifier = Modifier.padding(3.dp))

        Text(text = "by: ${comment.email}",
            fontSize = 13.sp,
            modifier = Modifier.padding(2.dp, 1.dp))
    }
}