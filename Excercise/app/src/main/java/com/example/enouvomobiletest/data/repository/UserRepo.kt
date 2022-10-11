package com.example.enouvomobiletest.data.repository

import androidx.lifecycle.LiveData
import com.example.enouvomobiletest.data.dao.UserDao
import com.example.enouvomobiletest.data.model.User

class UserRepo(private val userDao: UserDao) {

    val allUsers = userDao.getAll()

    suspend fun insert(user: User){
        userDao.insert(user)
    }

    fun checkLogin(email: String, pw: String): LiveData<Int> {
        return userDao.checkLogin(email, pw)
    }
}