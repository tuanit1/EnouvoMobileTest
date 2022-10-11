package com.example.enouvomobiletest.data.repository

import androidx.lifecycle.LiveData
import com.example.enouvomobiletest.data.dao.PostDao
import com.example.enouvomobiletest.data.model.FavoritePosts
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.relation.UserWithPosts

class PostRepo(private val postDao: PostDao) {

    val allPosts: LiveData<List<Post>> = postDao.getAllPost()
    val userWithPosts: LiveData<List<UserWithPosts>> = postDao.getUserWithPosts()

    fun getPage(page: Int, step: Int) = postDao.getPage(page, step)
    fun getFavoritePost(user_id: Int) = postDao.getFavoritePost(user_id)

    suspend fun setFavorite(post_id: Int, user_id: Int, isFav: Boolean) = postDao.setFavorite(FavoritePosts(post_id, user_id, isFav))
    suspend fun deleteAll() = postDao.deleteAll()

    fun getCrossFav(user_id: Int): LiveData<List<FavoritePosts>> = postDao.getCrossFav(user_id)

}