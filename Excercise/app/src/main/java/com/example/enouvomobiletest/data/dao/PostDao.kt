package com.example.enouvomobiletest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.enouvomobiletest.data.model.FavoritePosts
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.relation.PostWithFavoriteUsers
import com.example.enouvomobiletest.data.model.relation.PostWithUser
import com.example.enouvomobiletest.data.model.relation.UserWithPosts

@Dao
interface PostDao {
    @Query("SELECT * FROM post ORDER BY post_id DESC")
    fun getAllPost(): LiveData<List<Post>>

    @Query("SELECT DISTINCT * FROM post " +
            "JOIN user ON post.user_id = user.user_id " +
            "ORDER BY post_id LIMIT :page*:step, :step")
    fun getPage(page: Int, step: Int): LiveData<List<PostWithUser>>

    @Transaction
    @Query("SELECT DISTINCT Post.*, User.* FROM user " +
            "JOIN FavoritePosts ON FavoritePosts.user_id = user.user_id " +
            "JOIN Post ON FavoritePosts.post_id = Post.post_id " +
            "WHERE user.user_id = :user_id")
    fun getFavoritePost(user_id: Int): LiveData<List<PostWithUser>>

    @Transaction
    @Query("SELECT DISTINCT * FROM user " +
            "JOIN FavoritePosts ON FavoritePosts.user_id = user.user_id " +
            "JOIN Post ON FavoritePosts.post_id = Post.post_id")
    fun getPostWithFavoriteUsers(): List<PostWithFavoriteUsers>

    @Query("SELECT COUNT(*) FROM FavoritePosts " +
            "WHERE user_id= :user_id AND post_id = :post_id")
    fun isFavourite(user_id: Int, post_id: Int): Int

    @Query("SELECT * FROM FavoritePosts WHERE user_id = :user_id")
    fun getCrossFav(user_id: Int): LiveData<List<FavoritePosts>>

    @Transaction
    @Query("SELECT * FROM user")
    fun getUserWithPosts(): LiveData<List<UserWithPosts>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setFavorite(fav: FavoritePosts)

    @Query("DELETE FROM FavoritePosts WHERE user_id = :user_id AND post_id = :post_id")
    suspend fun removeFavorite(post_id: Int, user_id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: Post)

    @Query("DELETE FROM post")
    suspend fun deleteAll()

}
