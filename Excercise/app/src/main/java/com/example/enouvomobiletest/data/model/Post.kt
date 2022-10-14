package com.example.enouvomobiletest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) val post_id: Int?,
    val user_id: Int,
    val content: String,
    val timestamp: String,
)
