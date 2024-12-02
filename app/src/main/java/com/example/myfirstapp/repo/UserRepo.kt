package com.example.myfirstapp.repo

import com.example.myfirstapp.models.User
import com.example.myfirstapp.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepo(
    private val api: ApiService
) {
    fun getUsers(): Flow<List<User>> {
        return flow {
            emit(api.getUsers())
        }.flowOn(Dispatchers.IO)
    }

    fun getUser(id: Int): Flow<User?> {
        return flow {
            emit(api.getUser(id))
        }.flowOn(Dispatchers.IO)
    }

    fun updateUser(id: Int, updateUser: User): Flow<User?> {
        return flow {
            emit(api.updateUser(id, updateUser))
        }.flowOn(Dispatchers.IO)
    }
}