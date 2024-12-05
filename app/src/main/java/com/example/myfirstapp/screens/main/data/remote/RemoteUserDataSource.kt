package com.example.myfirstapp.screens.main.data.remote

import com.example.myfirstapp.screens.main.data.remote.model.RemoteUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteUserDataSource(
    private val api: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getUsers(): List<RemoteUser> =
        withContext(ioDispatcher) {
            api.getUsers()
        }

    suspend fun getUser(id: Long): RemoteUser =
        withContext(ioDispatcher) {
            api.getUser(id)
        }


    suspend fun updateUser(id: Long, updateUser: RemoteUser): RemoteUser? =
        withContext(ioDispatcher) {
            api.updateUser(id, updateUser)
        }
}