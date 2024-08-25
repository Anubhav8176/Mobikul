package com.anucodes.webkulassignment.screentwo

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anucodes.webkulassignment.Model.PostModel
import com.anucodes.webkulassignment.ScreenThree
import com.anucodes.webkulassignment.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPosts(
    modifier: Modifier,
    navController: NavController,
    postViewModel: PostViewModel,
    userId: Int
){
    val post by postViewModel.post.collectAsState()
    val text by postViewModel.text.collectAsState()
    val active by postViewModel.isSearching.collectAsState()
    val filteredPost by postViewModel.postResult.collectAsState()

    LaunchedEffect(key1 = Unit) {
        postViewModel.fetchPosts(userId)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ){

        DockedSearchBar(
            query = text,
            onQueryChange = postViewModel::updateSearchQuery,
            onSearch = postViewModel::updateSearchQuery,
            active = active,
            onActiveChange = {postViewModel.toggleSearch()},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = {
                Text(text = "Enter the username")
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Seacrch Icon") },
            trailingIcon = {
                if (active){
                    Icon(
                        modifier = Modifier
                            .clickable {
                                if (text.isNotEmpty()){
                                    postViewModel.updateSearchQuery("")
                                }else{
                                    postViewModel.toggleSearch()
                                }
                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cancel Icon")
                }
            }
        ) {
            LazyColumn {
                items(filteredPost){item ->
                    UserPostItem(post = item)
                }
            }
        }

        if (!active){
            LazyColumn {
                items(post){item ->
                    UserPostItem(item)
                }
            }
        }
    }
}


@Composable
fun UserPostItem(post: PostModel){
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        onClick = {
            val intent = Intent(context, ScreenThree::class.java).apply {
                putExtra("post", post)
            }
            context.startActivity(intent)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ){
            Text(text = post.title,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.size(5.dp))

            Text(text = post.body,
                fontSize = 18.sp,
                maxLines = 2
            )

        }
    }
}