package com.anucodes.webkulassignment.screenthree

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.webkulassignment.Model.PostModel
import com.anucodes.webkulassignment.viewmodel.PostViewModel


@Composable
fun PostDetail(modifier: Modifier,
               postViewModel: PostViewModel,
               post: PostModel?
){

    val comment by postViewModel.comment.collectAsState()

    LaunchedEffect(key1 = Unit) {
        postViewModel.fetchComments(post!!.id)
    }


    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                if (post != null){
                    Text(text = post.title,
                        fontSize = 25.sp)
                    Spacer(modifier = Modifier.size(10.dp))

                    Text(text = post.body,
                        fontSize = 16.sp)
                    Spacer(modifier = Modifier.size(10.dp))

                    Text(text = "Comments: ",
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.size(8.dp))

                    LazyColumn (
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        items(comment){comment->
                            CommentItem(comment)
                        }
                    }
                }else{
                    Text(
                        text = "Oops! Cant Load the Post",
                        modifier = modifier.fillMaxWidth(),
                        fontSize = 30.sp
                    )
                }
            }
        }

    }

}