package com.example.enouvomobiletest.ui.exam1.auth.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.enouvomobiletest.data.AppDatabase
import com.example.enouvomobiletest.data.model.User
import com.example.enouvomobiletest.data.repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModal(application: Application): AndroidViewModel(application) {
    private val repository: UserRepo
    private val allUsers: LiveData<List<User>>

    init {
        val dao = AppDatabase.getDatabase(application).getUserDao()
        repository = UserRepo(dao)
        allUsers = repository.allUsers
    }

    fun checkLogin(email: String, pw: String) = repository.checkLogin(email, pw)

    fun checkUserExist(email: String) = repository.checkUserExist(email)

    fun insertUser(user: User, callback: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)

        launch(Dispatchers.Main) {
            callback()
        }

    }

}