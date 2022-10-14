package com.example.enouvomobiletest.ui.exam1.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.enouvomobiletest.data.AppDatabase
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.relation.PostWithFavoriteUsers
import com.example.enouvomobiletest.data.model.relation.UserWithPosts
import com.example.enouvomobiletest.data.repository.PostRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModal(application: Application): AndroidViewModel(application) {
    private val allPosts: LiveData<List<Post>>
    private val userWithPosts: LiveData<List<UserWithPosts>>
    private val repository: PostRepo

    init {
        val dao = AppDatabase.getDatabase(application).getPostDao()
        repository = PostRepo(dao)
        allPosts = repository.allPosts
        userWithPosts = repository.userWithPosts
    }

    fun getPage(page: Int, step: Int) = repository.getPage(page, step)

    fun isFavourite(user_id: Int, post_id: Int, callBack: (Boolean) -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.isFavourite(user_id, post_id)

        withContext(Dispatchers.Main){
            callBack(result > 0)
        }
    }

    fun getPostWithFavoriteUsers(callBack: (list: List<PostWithFavoriteUsers>) -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        val result: List<PostWithFavoriteUsers> = repository.getPostWithFavoriteUsers()

        withContext(Dispatchers.Main){
            callBack(result)
        }
    }

    fun setFavorite(post_id: Int, user_id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.setFavorite(post_id, user_id)
    }

    fun removeFavorite(post_id: Int, user_id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.removeFavorite(post_id, user_id)
    }


}
