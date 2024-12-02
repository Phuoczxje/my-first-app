package com.example.myfirstapp.screens.main.data.remote


import com.example.myfirstapp.screens.main.data.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    suspend fun getUsers(): List<User>

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @PATCH("/users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body updateUser: User): User
}