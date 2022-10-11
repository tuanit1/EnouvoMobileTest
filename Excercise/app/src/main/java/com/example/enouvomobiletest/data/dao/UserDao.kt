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

    @Insert
    fun insertAll(vararg users: User)

    @Query("DELETE FROM User")
    suspend fun deleteAll()


}