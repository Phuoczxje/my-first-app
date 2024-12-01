package com.example.myfirstapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.models.User
import com.example.myfirstapp.repo.UserRepo
import kotlinx.coroutines.launch

class UserViewModel(private val userRepo: UserRepo) : ViewModel() {
    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> = _userLiveData

    private val _userListLiveData = MutableLiveData<List<User>?>()
    val userListLiveData: LiveData<List<User>?> = _userListLiveData

    fun getUsers() {
        viewModelScope.launch {
            userRepo.getUsers().collect { userList ->
                _userListLiveData.value = userList
            }
        }
    }

    fun getUsername(id: Int) {
        viewModelScope.launch {
            userRepo.getUser(id).collect { user ->
                _userLiveData.value = user
            }
        }
    }

    fun updateUser(id: Int, updateUser: User) {
        viewModelScope.launch {
            userRepo.updateUser(id, updateUser).collect { user ->
                _userLiveData.value = user
            }
        }
    }
}