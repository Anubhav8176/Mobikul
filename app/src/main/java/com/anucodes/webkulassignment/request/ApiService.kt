package com.anucodes.webkulassignment.request

import com.anucodes.webkulassignment.Model.CommentModel
import com.anucodes.webkulassignment.Model.PostModel
import com.anucodes.webkulassignment.Model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun getAllUser(): List<UserModel>

    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserModel

    @GET("/users/{id}/posts")
    suspend fun getUserPost(@Path("id") id: Int): List<PostModel>

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int): List<CommentModel>

}