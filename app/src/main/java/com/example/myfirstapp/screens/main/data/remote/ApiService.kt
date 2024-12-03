package com.example.myfirstapp.screens.main.data.remote


import com.example.myfirstapp.screens.main.data.remote.model.RemoteUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    suspend fun getUsers(): List<RemoteUser>

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Long): RemoteUser

    @PATCH("/users/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body updateUser: RemoteUser): RemoteUser?
}