package com.anucodes.webkulassignment.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anucodes.webkulassignment.Model.UserModel
import com.anucodes.webkulassignment.request.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    private val _user = MutableStateFlow<UserModel?>(null)
    val user: StateFlow<UserModel?> = _user.asStateFlow()

    private val _users = MutableStateFlow<List<UserModel>>(emptyList())
    val users: StateFlow<List<UserModel>> = _users.asStateFlow()


    //Implementing the searching functionality.

    private val _isSearching=MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _text=MutableStateFlow("")
    val searchText = _text.asStateFlow()

    val searchResult: StateFlow<List<UserModel>> =
        combine(_users, _text){user, text->
            if(text.isBlank()){
                user
            }else{
                user.filter {
                    it.username.contains(text, ignoreCase = true) ||
                            it.id.toString() == text
                }
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        fetchUsers()
    }


    private fun fetchUsers(){
        viewModelScope.launch {
            try {
                val fetchedUsers = RetrofitInstance.api.getAllUser()
                _users.value = fetchedUsers
            }catch (e: Exception){
                Log.i("Failed to load user: ", e.toString())
            }
        }
    }

    fun fetchUserById(id: Int){
        viewModelScope.launch {
            try {
                val fetchedUser = RetrofitInstance.api.getUserById(id)
                _user.value = fetchedUser

            }catch (e: Exception){
                Log.i("Error encountered: ", e.toString())
            }

        }
    }

    fun updateSearchQuery(query: String){
        _text.value = query
    }

    fun toggleSearch(){
        _isSearching.value = !_isSearching.value
        if(!_isSearching.value){
            updateSearchQuery("")
        }
    }

}