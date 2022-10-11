package com.example.enouvomobiletest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM User")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM user WHERE email = :email AND password = :pw")
    fun checkLogin(email: String, pw: String): LiveData<Int>


    @Query("SELECT COUNT(*) FROM user WHERE email = :email AND password = :pw")
    fun checkLogin2(email: String, pw: String): Int

}