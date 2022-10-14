package com.example.enouvomobiletest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val user_id: Int?,
    val name: String,
    val email: String,
    val password: String
)
