package com.example.enouvomobiletest.data.repository

import androidx.lifecycle.LiveData
import com.example.enouvomobiletest.data.dao.PostDao
import com.example.enouvomobiletest.data.model.FavoritePosts
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.relation.PostWithFavoriteUsers
import com.example.enouvomobiletest.data.model.relation.UserWithPosts

class PostRepo(private val postDao: PostDao) {

    val allPosts: LiveData<List<Post>> = postDao.getAllPost()

    val userWithPosts: LiveData<List<UserWithPosts>> = postDao.getUserWithPosts()

    fun getPage(page: Int, step: Int) = postDao.getPage(page, step)

    fun getFavoritePost(user_id: Int) = postDao.getFavoritePost(user_id)

    fun getPostWithFavoriteUsers(): List<PostWithFavoriteUsers> = postDao.getPostWithFavoriteUsers()

    fun getCrossFav(user_id: Int): LiveData<List<FavoritePosts>> = postDao.getCrossFav(user_id)

    fun isFavourite(user_id: Int, post_id: Int) = postDao.isFavourite(user_id, post_id)

    suspend fun setFavorite(post_id: Int, user_id: Int) = postDao.setFavorite(FavoritePosts(post_id, user_id))

    suspend fun removeFavorite(post_id: Int, user_id: Int) = postDao.removeFavorite(post_id, user_id)

}
