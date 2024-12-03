package com.example.myfirstapp.screens.main.data.repo

import com.example.myfirstapp.common.mapper.toRemoteUser
import com.example.myfirstapp.common.mapper.toUser
import com.example.myfirstapp.common.mapper.toUserEntity
import com.example.myfirstapp.screens.main.data.local.LocalUserDataSource
import com.example.myfirstapp.screens.main.data.models.User
import com.example.myfirstapp.screens.main.data.remote.RemoteUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepoImpl(
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localUserDataSource: LocalUserDataSource
) : UserRepository {
    override fun getUsers(): Flow<List<User>> {
        return flow {
            var users = localUserDataSource.getUsers().map { it.toUser() }

            if (users.isNotEmpty()) {
                emit(users)
                return@flow
            }

            users = remoteUserDataSource.getUsers().map { it.toUser() }
            localUserDataSource.insertUsers(users.map { it.toUserEntity() })
            emit(users)
        }
    }

    override fun getUser(id: Long): Flow<User> {
        return flow {
            var user = localUserDataSource.getUser(id)?.toUser()

            if (user != null) {
                emit(value = user)
                return@flow
            }

            user = remoteUserDataSource.getUser(id).toUser()
            emit(value = user)
        }
    }

    override fun updateUser(updateUser: User): Flow<User?> {
        return flow {
            val isUpdated = localUserDataSource.updateUser(updateUser.toUserEntity())
            val user = remoteUserDataSource.updateUser(updateUser.id, updateUser.toRemoteUser())

            if (isUpdated != 0) {
                emit(localUserDataSource.getUser(updateUser.id)?.toUser())
                return@flow
            }

            user?.apply {
                emit(value = user.toUser())
            }
        }
    }
}