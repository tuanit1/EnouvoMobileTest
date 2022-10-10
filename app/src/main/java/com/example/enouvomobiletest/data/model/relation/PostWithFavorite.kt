package com.example.enouvomobiletest.data.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.enouvomobiletest.data.model.FavoritePosts
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.User

data class PostWithFavorite(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "post_id",
        associateBy = Junction(FavoritePosts::class)
    )
    val posts: List<Post>
)