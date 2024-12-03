package com.example.myfirstapp.screens.main.data.repo

import com.example.myfirstapp.screens.main.data.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    fun getUser(id: Long): Flow<User>
    fun updateUser(updateUser: User): Flow<User?>
}