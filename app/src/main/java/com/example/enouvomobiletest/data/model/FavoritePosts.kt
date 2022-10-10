package com.example.enouvomobiletest.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["post_id", "user_id"])
data class FavoritePosts(
    val post_id: Int,
    val user_id: Int,
    val isFav: Boolean
)