package com.example.myfirstapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstapp.models.UserModel
import com.example.myfirstapp.repositories.UserRepo

class UserViewModel : ViewModel() {
    private val _userData = MutableLiveData<UserModel?>()
    private val userRepo = UserRepo()

    val userData: LiveData<UserModel?> = _userData

    fun getUsername() =
        _userData.apply {
            value = userRepo.getUsername().value
        }

    fun updateUsername(name: String) =
        _userData.apply {
            value = userRepo.updateUsername(name).value
        }
}