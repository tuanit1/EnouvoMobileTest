package com.example.enouvomobiletest.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.User

data class PostWithUser(
    @Embedded val post: Post,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id",
    )
    val user: User
)
