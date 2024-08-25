package com.anucodes.webkulassignment.screentwo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    var title: String,
    var icon: ImageVector,
    var screen_route: String
) {
    object Profile: BottomNavigationItems("Profile", Icons.Filled.AccountCircle, "user_profile")
    object Posts: BottomNavigationItems("Posts", Icons.Default.DateRange, "user_posts")
}