package com.anucodes.webkulassignment

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anucodes.webkulassignment.Model.PostModel
import com.anucodes.webkulassignment.Model.UserModel
import com.anucodes.webkulassignment.screentwo.BottomNavigation
import com.anucodes.webkulassignment.screentwo.UserPosts
import com.anucodes.webkulassignment.screentwo.UserProfile
import com.anucodes.webkulassignment.ui.theme.WebkulAssignmentTheme
import com.anucodes.webkulassignment.viewmodel.PostViewModel
import com.anucodes.webkulassignment.viewmodel.UserViewModel

class ScreenTwo : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userViewModel = UserViewModel()
        val postViewModel = PostViewModel()

        val userId = intent.getIntExtra("userId", -1)

        setContent {
            WebkulAssignmentTheme {

                val navController = rememberNavController()

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
                    },
                    bottomBar = {
                        BottomNavigation(navController)
                    }
                ){ innerPadding ->
                    NavHost(navController = navController, startDestination = "user_profile" ) {
                        composable("user_profile") {
                            UserProfile(Modifier.padding(innerPadding), userViewModel, userId)
                        }
                        composable("user_posts"){
                            UserPosts(Modifier.padding(innerPadding), navController = navController, postViewModel, userId)
                        }
                    }
                }
            }
        }
    }
}

