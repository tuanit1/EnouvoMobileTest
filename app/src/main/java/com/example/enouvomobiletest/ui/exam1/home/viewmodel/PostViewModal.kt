package com.example.enouvomobiletest.ui.exam1.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.enouvomobiletest.data.AppDatabase
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.relation.UserWithPosts
import com.example.enouvomobiletest.data.repository.PostRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModal(application: Application): AndroidViewModel(application) {
    val allPosts: LiveData<List<Post>>
    val userWithPosts: LiveData<List<UserWithPosts>>
    val repository: PostRepo

    init {
        val dao = AppDatabase.getDatabase(application).getPostDao()
        repository = PostRepo(dao)
        allPosts = repository.allPosts
        userWithPosts = repository.userWithPosts
    }

    fun getFavouritePosts(user_id: Int) = repository.getFavoritePost(user_id)
    fun getPage(page: Int, step: Int) = repository.getPage(page, step)
    fun getCrossFav(user_id: Int) = repository.getCrossFav(user_id)

    fun setFavorite(post_id: Int, user_id: Int, isFav: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repository.setFavorite(post_id, user_id, isFav)
    }


}