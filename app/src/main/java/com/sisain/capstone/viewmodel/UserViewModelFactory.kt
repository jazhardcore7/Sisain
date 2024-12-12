package com.sisain.capstone.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.sisain.capstone.data.userlocaldb.UserDao
import com.sisain.capstone.data.userlocaldb.repository.UserRepository
import com.sisain.capstone.viewmodel.UserViewModel

class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(UserRepository(userDao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}