package com.example.myfirstapp.screens.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.screens.main.data.models.User
import com.example.myfirstapp.screens.main.data.repo.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepo: UserRepository,
) : ViewModel() {
    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> = _userLiveData

    private val _usersState = MutableStateFlow<List<User>>(emptyList())
    val usersLiveData: LiveData<List<User>> = _usersState.asLiveData()

    fun getUsers() {
        viewModelScope.launch {
            userRepo.getUsers().collect { userList ->
                _usersState.value = userList
            }
        }
    }

    fun getUsername(id: Long) {
        viewModelScope.launch {
            userRepo.getUser(id).collect { user ->
                _userLiveData.value = user
            }
        }
    }

    fun updateUser(updateUser: User) {
        viewModelScope.launch {
            userRepo.updateUser(updateUser).collect { user ->
                _userLiveData.value = user
            }
        }
    }
}