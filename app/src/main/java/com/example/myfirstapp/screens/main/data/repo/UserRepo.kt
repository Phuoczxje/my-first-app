package com.example.myfirstapp.screens.main.data.repo

import android.util.Log
import com.example.myfirstapp.screens.main.data.local.UserDao
import com.example.myfirstapp.screens.main.data.models.User
import com.example.myfirstapp.screens.main.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepo(
    private val api: ApiService,
    private val userDao: UserDao
) {
    fun getUsers(): Flow<List<User>> {
        return flow {
            val users = userDao.getAllUsers()

            if (users.isNotEmpty()) {
                emit(users)
                return@flow
            }

            Log.e("AAA", "getUsers: ${api.getUsers()}")
            emit(api.getUsers())
            userDao.insertUsers(users)
            return@flow
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