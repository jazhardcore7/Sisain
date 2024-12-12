package com.sisain.capstone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisain.capstone.data.userlocaldb.User
import com.sisain.capstone.data.userlocaldb.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun register(user: User, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                userRepository.insert(user)
                onComplete(true)
            } catch (e: Exception) {
                onComplete(false)
            }
        }
    }

    fun login(email: String, password: String, onComplete: (User?) -> Unit) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                userRepository.login(email, password)
            }
            onComplete(user)
        }
    }
}