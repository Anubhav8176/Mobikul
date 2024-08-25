package com.anucodes.webkulassignment.screenone

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.webkulassignment.Model.UserModel
import com.anucodes.webkulassignment.ScreenTwo
import com.anucodes.webkulassignment.viewmodel.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(userViewModel: UserViewModel, modifier: Modifier) {
    val users by userViewModel.users.collectAsState()
    val text by userViewModel.searchText.collectAsState()
    val active by userViewModel.isSearching.collectAsState()
    val filteredUser by userViewModel.searchResult.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        DockedSearchBar(
            query = text,
            onQueryChange = userViewModel::updateSearchQuery,
            onSearch = userViewModel::updateSearchQuery,
            active = active,
            onActiveChange = {
                userViewModel.toggleSearch()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = {
                Text(text = "Enter the username")
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Seacrch Icon")},
            trailingIcon = {
                if(active){
                    Icon(
                        modifier = Modifier
                            .clickable {
                                if (text.isNotEmpty()){
                                    userViewModel.updateSearchQuery("")
                                }else{
                                    userViewModel.toggleSearch()
                                }
                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cancel Icon")
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ){
                items(filteredUser){
                    UserListItem(user = it)
                }
            }
        }

        if(!active){
            LazyColumn (
            ){
                items(users){user->
                    UserListItem(user)
                }
            }
        }
    }
}


@Composable
fun UserListItem(user: UserModel){
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        onClick = {
            val intent = Intent(context, ScreenTwo::class.java).apply {
                putExtra("userId", user.id)
            }
            context.startActivity(intent)
        }
    ) {
        Text(text = "Name: ${user.name}",
            modifier = Modifier.padding(5.dp, 1.dp),
            fontSize = 20.sp)
        Spacer(modifier = Modifier.size(2.dp))

        Text(text = "username: ${user.username}",
            modifier = Modifier.padding(5.dp, 1.dp))
        Spacer(modifier = Modifier.size(2.dp))

        Text(text = "Email: ${user.email}",
            modifier = Modifier.padding(5.dp, 1.dp))
        Spacer(modifier = Modifier.size(2.dp))

        Text(text = "Phone: ${user.phone}",
            modifier = Modifier.padding(5.dp, 1.dp))
        Spacer(modifier = Modifier.size(2.dp))

        Text(text = "Website: ${user.website}",
            modifier = Modifier.padding(5.dp, 1.dp))
        Spacer(modifier = Modifier.size(2.dp))

        Text(text = "Address: ${user.address.street + user.address.city}",
            modifier = Modifier.padding(5.dp, 1.dp))
        Spacer(modifier = Modifier.size(2.dp))

        Text(text = "Company: ${user.company.name}",
            modifier = Modifier.padding(5.dp, 1.dp))
    }
}

@Composable
fun SearchedUser(filteredUser: List<UserModel>){
    LazyColumn {
        items(filteredUser){fUser->
            UserListItem(user = fUser)
        }
    }
}