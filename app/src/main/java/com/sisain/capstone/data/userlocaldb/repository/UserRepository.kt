package com.sisain.capstone.data.userlocaldb.repository

import com.sisain.capstone.data.userlocaldb.User
import com.sisain.capstone.data.userlocaldb.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }
}