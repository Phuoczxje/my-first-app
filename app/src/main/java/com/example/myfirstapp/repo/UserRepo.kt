package com.example.myfirstapp.repo

import com.example.myfirstapp.models.User
import com.example.myfirstapp.services.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepo(
    private val api: ApiService
) {
    fun getUsers(): Flow<List<User>> {
        return flow {
            emit(api.getUsers())
        }
    }

    fun getUser(id: Int): Flow<User?> {
        return flow {
            emit(api.getUser(id))
        }
    }

    fun updateUser(id: Int, updateUser: User): Flow<User?> {
        return flow {
            emit(api.updateUser(id, updateUser))
        }
    }
}