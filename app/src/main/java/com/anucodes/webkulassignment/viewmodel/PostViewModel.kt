package com.anucodes.webkulassignment.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anucodes.webkulassignment.Model.CommentModel
import com.anucodes.webkulassignment.Model.PostModel
import com.anucodes.webkulassignment.request.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {

    private val _comments = MutableStateFlow<List<CommentModel>>(emptyList())
    val comment: StateFlow<List<CommentModel>> = _comments.asStateFlow()

    private val _posts = MutableStateFlow<List<PostModel>>(emptyList())
    val post: StateFlow<List<PostModel>> = _posts.asStateFlow()


    //For adding the searching in the posts.
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()


    val postResult: StateFlow<List<PostModel>> =
        combine(_posts, _text){posts, texts->
            if (texts.isBlank()){
                posts
            }else{
                posts.filter {
                    it.title.contains(texts, ignoreCase = true) ||
                            it.id.toString() == texts
                }
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun fetchPosts(id: Int){
        viewModelScope.launch {
            try {
                val fetchedPosts = RetrofitInstance.api.getUserPost(id)
                _posts.value = fetchedPosts
            }catch (e: Exception){
                Log.i("Error encountered: ", e.toString())
            }
        }
    }


    fun fetchComments(id: Int){
        viewModelScope.launch {
            try {
                val fetchedComments = RetrofitInstance.api.getComments(id)
                _comments.value = fetchedComments
            }catch (e: Exception){
                Log.i("Error in comments: ", e.toString())
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