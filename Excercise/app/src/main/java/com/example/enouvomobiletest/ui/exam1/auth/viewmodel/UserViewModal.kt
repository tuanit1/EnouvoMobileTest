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

class UserViewModal(application: Application): AndroidViewModel(application) {
    val repository: UserRepo
    val allUsers: LiveData<List<User>>

    init {
        val dao = AppDatabase.getDatabase(application).getUserDao()
        repository = UserRepo(dao)
        allUsers = repository.allUsers
    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    fun checkLogin(email: String, pw: String) = repository.checkLogin(email, pw)

    fun checkLogin2(email: String, pw: String, callback: (Boolean) -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.checkLogin2(email, pw) > 0

        launch(Dispatchers.Main){
            callback(result)
        }

    }
}