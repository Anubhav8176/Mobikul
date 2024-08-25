package com.anucodes.webkulassignment.screentwo

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigation(navController: NavController){
    val items = listOf(
        BottomNavigationItems.Profile,
        BottomNavigationItems.Posts
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        items.forEach{item->
            NavigationBarItem(
                label = { Text(text = item.title)},
                alwaysShowLabel = true,
                selected = currentDestination == item.screen_route,
                icon = { Icon(imageVector = item.icon, contentDescription = item.title)},
                onClick = {
                    navController.navigate(item.screen_route){
                        navController.graph.startDestinationRoute?.let { route->
                            popUpTo(route = route){
                                saveState=true
                            }
                        }
                        launchSingleTop=true
                        restoreState=true
                    }
                }
            )
        }

    }
}