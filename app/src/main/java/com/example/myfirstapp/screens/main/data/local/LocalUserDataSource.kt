package com.example.myfirstapp.screens.main.data.local

import com.example.myfirstapp.screens.main.data.local.entity.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalUserDataSource(
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getUsers(): List<UserEntity> =
        withContext(ioDispatcher) {
            userDao.getUsers()
        }

    suspend fun getUser(id: Long): UserEntity? =
        withContext(ioDispatcher) {
            userDao.getUser(id)
        }

    suspend fun updateUser(updateUser: UserEntity): Int =
        withContext(ioDispatcher) {
            userDao.updateUser(updateUser)
        }

    suspend fun insertUsers(users: List<UserEntity>) =
        withContext(ioDispatcher) {
            userDao.insertUsers(users)
        }
}