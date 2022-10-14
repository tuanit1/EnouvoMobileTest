package com.example.enouvomobiletest.data.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.enouvomobiletest.data.model.FavoritePosts
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.User

data class PostWithFavoriteUsers(
    @Embedded val post: Post,
    @Relation(
        parentColumn = "post_id",
        entityColumn = "user_id",
        associateBy = Junction(FavoritePosts::class)
    )
    val favUser: List<User>,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val user: User,
)
