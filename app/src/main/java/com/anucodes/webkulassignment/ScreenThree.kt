package com.anucodes.webkulassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.webkulassignment.Model.PostModel
import com.anucodes.webkulassignment.screenthree.PostDetail
import com.anucodes.webkulassignment.screentwo.BottomNavigation
import com.anucodes.webkulassignment.ui.theme.WebkulAssignmentTheme
import com.anucodes.webkulassignment.viewmodel.PostViewModel

class ScreenThree : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val postViewModel = PostViewModel()

        val post = intent.getSerializableExtra("post")as?PostModel

        setContent {
            WebkulAssignmentTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary
                            ),
                            title = {
                            Text(text = "Mobikul",
                                fontSize = 30.sp,
                                modifier = Modifier.padding(5.dp))
                        })
                    }
                ) { innerPadding ->
                    PostDetail(modifier = Modifier.padding(innerPadding), postViewModel, post)
                }
            }
        }
    }
}
